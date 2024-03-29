#coding=utf-8

from __future__ import print_function
import os
import math
import random
from socket import *
import struct

#cap = cv2.VideoCapture(0)
import sys
import rospy
import cv2
from math import radians
from geometry_msgs.msg import Twist
from std_msgs.msg import String
from sensor_msgs.msg import Image
from cv_bridge import CvBridge, CvBridgeError

class Turtlebot:
    def __init__(self):

        rospy.init_node('follow', anonymous=False)

        self.bridge = CvBridge()
        self.image_received = False

        # Connect image topic
        img_topic = "/camera/rgb/image_raw"
        self.topic = img_topic
        self.image_sub = rospy.Subscriber(img_topic, Image, self.callback)
        self.cmd_vel = rospy.Publisher('cmd_vel_mux/input/navi', Twist, queue_size=10)
        self.rate = rospy.Rate(5)
        # Allow up to one second to connection
        rospy.sleep(1)

    def callback(self, data):

        # Convert image to OpenCV format
        try:
            cv_image = self.bridge.imgmsg_to_cv2(data, "bgr8")
        except CvBridgeError as e:
            print(e)

        self.image_received = True
        self.image = cv_image

    def take_picture(self, img_title):
        if self.image_received:
            # Save an image
            cv2.imwrite(img_title, self.image)
            return True
        else:
            return False

    def forward(self):
        move_cmd = Twist()
        move_cmd.linear.x = 0.4

        rospy.loginfo("Going Straight")
        for x in range(0,5):
            self.cmd_vel.publish(move_cmd)
            self.rate.sleep()

    def left_turn(self):
        turn_cmd = Twist()
        turn_cmd.linear.x = 0
        turn_cmd.angular.z = radians(30) #45 deg/s in radians/s

        rospy.loginfo("Turning")
        for x in range(0,3):
            self.cmd_vel.publish(turn_cmd)
            self.rate.sleep() 

    def right_turn(self):
        turn_cmd = Twist()
        turn_cmd.linear.x = 0
        turn_cmd.angular.z = radians(-30) #-45 deg/s in radians/s

        rospy.loginfo("Turning")
        for x in range(0,3):
            self.cmd_vel.publish(turn_cmd)
            self.rate.sleep() 

    def stop(self):
        turn_cmd = Twist()
        turn_cmd.linear.x = 0
        turn_cmd.angular.z = 0
        self.cmd_vel.publish(turn_cmd)
        self.rate.sleep() 

    def move(self, cmd):
        if cmd == "forward":
            self.forward()
        elif cmd == "left":
            self.right_turn()
        elif cmd == "right":
            self.left_turn()
        elif cmd == "error":
            return "error"
        elif cmd == "stop":
            self.stop()
        else:
            print("unknow command: " + cmd)

    def update_frame(self):
        self.image_sub = rospy.Subscriber(self.topic, Image, self.callback)

    def is_image_received(self):
        return self.image_received
    
    def get_frame(self):
        return self.image

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
    tcp_ip = '192.168.51.40'
    #tcp_ip = '192.168.2.222'
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
            print ('filepath: '+filename)

            fp = open(filename, 'rb')
            while 1:
                data = fp.read(1024)
                if not data:
                    print(filename+' file send over...')
                    break
                tcp_socket.send(data)
        msg = tcp_socket.recv(1024).decode()
        print(msg)
        tcp_socket.close()
        break
    return msg

if __name__ == '__main__':

    # Initialize
    turtlebot = Turtlebot()

    num=0

    while True:
        # get a frame
        #rval, frame = cap.read()
        # save a frame
        #if rval==True:
          #  frame = cv2.flip(frame,0)
            #num=num+1
            #print(num)
        #else:
            #break
        # show a frame
        file_name = str(num) + '.jpg'
        #cv2.imwrite(file_name, frame)
        if turtlebot.take_picture(file_name):
            num = num + 1
            rospy.loginfo(num)
            rospy.loginfo("Saved image " + file_name)
            turtlebot.update_frame()
        else:
            rospy.loginfo("No images received from turtlebot")
            break
        cmd = tcp_send(file_name)
        print(cmd)
        if cmd == "error":
            break
        turtlebot.move(cmd)
        cv2.imshow("capture", turtlebot.get_frame())
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
        #if num == 5:
            #break
    #cap.release()
    #out.release()
    cv2.destroyAllWindows()
    rospy.loginfo("everything closed")

    # Take a photo

    # Use '_image_title' parameter from command line
    # Default value is 'photo.jpg'
    #img_title = rospy.get_param('~image_title', 'photo.jpg')

    

    # Sleep to give the last log messages time to be sent
    rospy.sleep(1)

