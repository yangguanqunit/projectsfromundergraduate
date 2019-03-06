#coding=utf-8

import os
import math
import random
import cv2
from socket import *
import os
import struct

cap = cv2.VideoCapture(0)
 
def file_deal(file_name):
    try:
        files = open(file_name, "rb")
        mes = files.read()
    except:
        print("NO SUCH FILE")
    else:
        files.close()
        return mes

def tcp_send(filename):
    tcp_socket = socket(AF_INET, SOCK_STREAM)
    # 接收用输入的服务器端的ip和端口
    tcp_ip = '192.168.1.131'
    # tcp_ip = '192.168.2.222'
    tcp_port = 12345
    # 连接服务器
    tcp_socket.connect((tcp_ip, tcp_port))

    while True:
        #filepath = raw_input('please input file path: ')
        if os.path.isfile(filename):
            # 定义定义文件信息。128s表示文件名为128bytes长，l表示一个int或log文件类型，在此为文件大小
            fileinfo_size = struct.calcsize('128sl')
            # 定义文件头信息，包含文件名和文件大小
            fhead = struct.pack('128sl', os.path.basename(filename),
                                os.stat(filename).st_size)
            tcp_socket.send(fhead)
            print 'filepath: {0}'.format(filename)

            fp = open(filename, 'rb')
            while 1:
                data = fp.read(1024)
                if not data:
                    print '{0} file send over...'.format(filename)
                    break
                tcp_socket.send(data)

        print tcp_socket.recv(1024).decode()
        tcp_socket.close()
        break

num=0

while cap.isOpened():
    # get a frame
    rval, frame = cap.read()
    # save a frame
    if rval==True:
      #  frame = cv2.flip(frame,0)
        num=num+1
        print num
    else:
        break
    # show a frame
    file_name = str(num) + '.jpg'
    cv2.imwrite(file_name, frame)
    tcp_send(file_name)
    cv2.imshow("capture", frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
        if num == 5:
            break
cap.release()
#out.release()
cv2.destroyAllWindows()


