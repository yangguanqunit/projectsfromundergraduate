package serverForMessageOperation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import tools.ConstructUDP;
import tools.ShiftTagetIpToSourceIp;

public class ReceiveMessageTEST {
	int servPort = 9002;
	int ECHOMAX = 1024;
	public void run()throws IOException{
		DatagramSocket socket = new DatagramSocket(servPort);
		DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);	
		
		
		while(true){
			
			socket.receive(packet);
			System.out.println(new ShiftTagetIpToSourceIp().shiftToSourceIP(new String(packet.getData()), "192.168.1.2"));
			System.out.println(packet.getAddress());
			DatagramSocket client_2 = new DatagramSocket();
			DatagramPacket clientP_2 = new ConstructUDP("127.0.0.1", 9004, "1111").UDPsocket();
			client_2.send(clientP_2);
			socket.send(packet);
			packet.setLength(ECHOMAX);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		new ReceiveMessageTEST().run();

	}

}
