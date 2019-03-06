from socket import *
import pickle
import struct
import os
import sys


import math
import random

import numpy as np
import tensorflow as tf
import cv2

slim = tf.contrib.slim


import matplotlib.pyplot as plt
import matplotlib.image as mpimg
from PIL import Image

sys.path.append('../')

from nets import ssd_vgg_300, ssd_common, np_methods
from preprocessing import ssd_vgg_preprocessing
from notebooks import visualization

def process_image(isess, ssd_anchors, image_4d, predictions, localisations, bbox_img, img_input, img, select_threshold=0.5, nms_threshold=.45, net_shape=(300, 300)):
    # Run SSD network.
    rimg, rpredictions, rlocalisations, rbbox_img = isess.run([image_4d, predictions, localisations, bbox_img],
                                                              feed_dict={img_input: img})
    
    # Get classes and bboxes from the net outputs.
    rclasses, rscores, rbboxes = np_methods.ssd_bboxes_select(
            rpredictions, rlocalisations, ssd_anchors,
            select_threshold=select_threshold, img_shape=net_shape, num_classes=21, decode=True)
    
    rbboxes = np_methods.bboxes_clip(rbbox_img, rbboxes)
    rclasses, rscores, rbboxes = np_methods.bboxes_sort(rclasses, rscores, rbboxes, top_k=400)
    rclasses, rscores, rbboxes = np_methods.bboxes_nms(rclasses, rscores, rbboxes, nms_threshold=nms_threshold)
    # Resize bboxes to original image shape. Note: useless for Resize.WARP!
    rbboxes = np_methods.bboxes_resize(rbbox_img, rbboxes)
    return rclasses, rscores, rbboxes

def cmd(isess, ssd_anchors, image_4d, predictions, localisations, bbox_img, img_input, image_name):
    #path = '../demo/'
    #image_names = sorted(os.listdir(path))
    #image_name="car2.jpg"

    #img = mpimg.imread(path + image_names[-5])
    #img = mpimg.imread(image_name)
    #img_raw = Image.open(image_name)
    #img = np.array(img_raw)
    img = cv2.imread(image_name)
    rclasses, rscores, rbboxes =  process_image(isess, ssd_anchors, image_4d, predictions, localisations, bbox_img, img_input, img)

    # visualization.bboxes_draw_on_img(img, rclasses, rscores, rbboxes, visualization.colors_plasma)
    centerx = 0
    centerx = visualization.get_center_x(img, rclasses, rscores, rbboxes)
    width = img.shape[1]
    #left in (0, 40%], mid in (40%, 60%), right in [60%, 100%)
    left = width * 0.4
    right = width * 0.6
    
    print(centerx)
    if centerx == 0:
        print("nothing in sight")
        return "right"
    elif 0 < centerx and centerx <= left:
        return "left"
    elif left < centerx and centerx < right:
        return "middle"
    elif right <= centerx and centerx < width:
        return "right"
    else:
        print("danger centerx, out of picture")
        print("centerx: " + centerx)
        print("left: " + left)
        print("right: " + right)
        print("width: " + width)
        return "danger"

def file_deal(file_name):
    # 定义函数用于处理用户索要下载的文件
    try:
        # 二进制方式读取
        files = open(file_name, "rb")
        mes = files.read()
    except:
        print("没有该文件")
    else:
        files.close()
        return mes

def commamd():
    #return str(1231231)
    #msg = "forward"
    #msg = "left"
    #msg = "right"
    return msg

def get_cmd(isess, ssd_anchors, image_4d, predictions, localisations, bbox_img, img_input, image_name):
    cmd_str = cmd(isess, ssd_anchors, image_4d, predictions, localisations, bbox_img, img_input, image_name)
    if cmd_str == "left":
        return "right"
    elif cmd_str == "middle":
        return "forward"
    elif cmd_str == "right":
        return "left"
    elif cmd_str == "stop":
        return "stop"
    else:
        print("error processing frame")
        return "error"


def main():
    # TensorFlow session: grow memory when needed. TF, DO NOT USE ALL MY GPU MEMORY!!!
    gpu_options = tf.GPUOptions(allow_growth=True)
    config = tf.ConfigProto(log_device_placement=False, gpu_options=gpu_options)
    isess = tf.InteractiveSession(config=config)

    # Input placeholder.
    net_shape = (300, 300)
    data_format = 'NHWC'
    img_input = tf.placeholder(tf.uint8, shape=(None, None, 3))
    # Evaluation pre-processing: resize to SSD net shape.
    image_pre, labels_pre, bboxes_pre, bbox_img = ssd_vgg_preprocessing.preprocess_for_eval(
        img_input, None, None, net_shape, data_format, resize=ssd_vgg_preprocessing.Resize.WARP_RESIZE)
    image_4d = tf.expand_dims(image_pre, 0)

    # Define the SSD model.
    reuse = True if 'ssd_net' in locals() else None
    ssd_net = ssd_vgg_300.SSDNet()
    with slim.arg_scope(ssd_net.arg_scope(data_format=data_format)):
        predictions, localisations, _, _ = ssd_net.net(image_4d, is_training=False, reuse=reuse)

    # Restore SSD model.
    ckpt_filename = '../checkpoints/ssd_300_vgg.ckpt'
    # ckpt_filename = '../checkpoints/VGG_VOC0712_SSD_300x300_ft_iter_120000.ckpt'
    isess.run(tf.global_variables_initializer())
    saver = tf.train.Saver()
    saver.restore(isess, ckpt_filename)

    # SSD default anchor boxes.
    ssd_anchors = ssd_net.anchors(net_shape)

    # Main image processing routine.
    print("init ready. net restored")

    # Test on some demo image and visualize output.
    tcp_socket = socket(AF_INET, SOCK_STREAM)
    tcp_socket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
    # 固定端口号
    tcp_socket.bind(("",12345))
    # 将主动套接字转为被动套接字
    tcp_socket.listen(128)

    print("ready to receive")
    
    while True:
        client_socket,client_addr = tcp_socket.accept()
        #conn, addr = s.accept()
        print ('Accept new connection from ', client_addr)
        new_filename = ""
        while True:
            fileinfo_size = struct.calcsize('128sl')
            buf = client_socket.recv(fileinfo_size)
            if buf:
                filename, filesize = struct.unpack('128sl', buf)
                #print(filename)
                fn = filename.decode().strip('\00')
                new_filename = os.path.join('./', fn)
                print(fn)
                print ('file new name is '+new_filename+ ', filesize is '+str(filesize))

                recvd_size = 0  # 定义已接收文件的大小
                fp = open(new_filename, 'wb')
                print('start receiving...')

                while not recvd_size == filesize:
                    if filesize - recvd_size > 1024:
                        data = client_socket.recv(1024)
                        recvd_size += len(data)
                    else:
                        data = client_socket.recv(filesize - recvd_size)
                        recvd_size = filesize
                    fp.write(data)
                fp.close()
                print('end receive...')

            #client_socket.send(commamd.encode())
            cmd_str = get_cmd(isess, ssd_anchors, image_4d, predictions, localisations, bbox_img, img_input, new_filename)
            client_socket.send(cmd_str.encode())
            print("end sending")
            client_socket.close()
            break
    

if __name__ == "__main__":
    main()
