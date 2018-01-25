package clientForMessageOperation;

import java.net.*;

import tools.BinayToStringOrInt;
import tools.ConstructUDP;
import tools.StringToBinary;

import java.io.*;

public class SendMessageTEST {
	private DatagramSocket socket = null;
	private DatagramPacket sendPacket;
	private DatagramPacket receivePacket;
	final int TIMEOUT=60*60*10;
	private InetAddress serverAddress = null;
	private int MAXTRIES = 10;
	private int servPort=9002;
	byte[] bytesToSend = new byte[1024];
	public  SendMessageTEST() throws SocketException, UnknownHostException {
		socket = new DatagramSocket();
		socket.setSoTimeout(TIMEOUT);
		serverAddress = InetAddress.getByAddress(new byte[]{127,0,0,1});
		StringToBinary stringToBinary_tool = new StringToBinary();
		ConstructMessageBody constructMessageBody_tool = new ConstructMessageBody();
		String message = "hahaha";
		message = constructMessageBody_tool.messageBody(stringToBinary_tool.
				shiftForIP(constructMessageBody_tool.getLocalAddr()),stringToBinary_tool.
				shiftForPort(constructMessageBody_tool.getLocalPort(socket)) , message);
		sendPacket = new ConstructUDP("127.0.0.1", servPort,message).UDPsocket();
		receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
		
	}
	public void run() throws IOException{
		int tries = 0;
		boolean receivedResponse =false;
		do{
			socket.send(sendPacket);
			try{
				socket.receive(receivePacket);
				if (!receivePacket.getAddress().equals(serverAddress)){
					throw new IOException("REceived unknown source");
				}
				receivedResponse  = true;
			}
			catch (InterruptedIOException e){
				tries+=1;
				System.out.println("TImed out");
			}
		}while((!receivedResponse)&&(tries<MAXTRIES));
		
		if(receivedResponse){
			System.out.println(new String(receivePacket.getData()));
		}
		else{
			System.out.println("NO");
		}
		socket.close();
		
	}
	
	static public void main(String[] argv) throws IOException{
		
		new SendMessageTEST().run();
	}
	
	
	
	

}
