package clientForMessageOperation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveMessageTEST {
	
	public static void main(String[] args)throws IOException{
		int ECHOMAX = 1024;
		DatagramSocket socket = new DatagramSocket(9004);
		DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
		System.out.println("test");
		socket.receive(packet);
		System.out.println(new String(packet.getData()));	
	}

}
