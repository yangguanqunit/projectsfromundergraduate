package tools;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConstructUDP {                             //工具类 构造UDP消息包
	
	private DatagramPacket UDPsocket; 
	
	public ConstructUDP(String address,int port,String message) throws UnknownHostException{
		
		this.UDPsocket = new DatagramPacket(message.getBytes(),message.getBytes().length,InetAddress.getByName(address),port);
	
	}
	public DatagramPacket UDPsocket(){
		return this.UDPsocket;
		
	}

}
