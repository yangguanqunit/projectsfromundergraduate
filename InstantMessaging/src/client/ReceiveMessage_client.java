package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveMessage_client extends Thread {
	
	private DatagramPacket receivePacket_client;
	private DatagramSocket receiveSocket_client;
	private  byte[] receiveBuf;
	private String message;
	public ReceiveMessage_client(DatagramSocket socekt){            //接收信息规范格式输出
		receiveBuf = new byte[1024];
		this.receiveSocket_client = socekt;
		this.receivePacket_client = new DatagramPacket(receiveBuf, receiveBuf.length);
//		System.out.println("error in here");
		
	}
	
	public void run(){
		while(true)
		{
			
			try {
				this.receiveSocket_client.receive(receivePacket_client);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			receivePacket_client.setData(receiveBuf);
			
				this.message = new String(receiveBuf);
				System.out.println("FROM : "+message.substring(0, 16));
				System.out.println("       "+message.substring(16, message.length()));
			
		}
		
	}
	
	

}
