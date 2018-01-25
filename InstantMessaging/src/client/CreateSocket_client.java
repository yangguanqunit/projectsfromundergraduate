package client;

import java.io.*;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class CreateSocket_client {
		
	private  Socket createSocket;
	private  PrintStream sendConfirmRequest;
	private  BufferedReader ReceiveConfirmResponse;
	
	public  CreateSocket_client()throws IOException{                          //建立TCPsocket连接
		createSocket = new Socket("127.0.0.1",30000);
		sendConfirmRequest=new PrintStream(createSocket.getOutputStream());
		ReceiveConfirmResponse=new BufferedReader(new InputStreamReader(createSocket.getInputStream()));	
	}
	
	public  Socket getClientSocket(){                   
		return createSocket;
	}
	public  PrintStream getPrintStream(){               //获取发送流
		return sendConfirmRequest;
	}
	public  BufferedReader getBufferedRead(){           //获取接收流
		return ReceiveConfirmResponse;
	}
	
	
}
