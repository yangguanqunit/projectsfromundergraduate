package client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.sun.xml.internal.ws.resources.ServerMessages;

import tools.ConstructUDP;
public class SendMessage extends Thread {               //构建消息发送UDPsocket    
	
	
	private DatagramSocket client_socket;
	private ConstructUDP clientSend_socket;
	private InputContain inputContain;                          
	private Scanner contain;
	
	
	public SendMessage(DatagramSocket sendPort){
		this.client_socket = sendPort;
		inputContain = new InputContain("ygq", "yang");             
		this.contain = new Scanner(System.in);
	}
	public void run(){
		while(true){
			
			try {
				clientSend_socket = new ConstructUDP("127.0.0.1", 29771, inputContain.sendPacket(contain.nextLine()));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				client_socket.send(clientSend_socket.UDPsocket());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
		}
	}
	
	
}
