package server;
import java.io.*;
import java.net.*;
public class CreateSocket_server {                                  //构建服务器UDPsocket
	private ServerSocket createServerSocket;
	private PrintStream sendConfirmData;
	private BufferedReader receiveRequestData;
	private Socket acceptClientRequest;
	public CreateSocket_server(int port)throws IOException{
		createServerSocket = new ServerSocket(port);
	}
	public  Socket acceptClientRequest()throws IOException{
		acceptClientRequest = createServerSocket.accept();
		return acceptClientRequest;
	}
	public BufferedReader getBufferedReader(){
		return receiveRequestData;
	}
	public PrintStream getPrintStream(){
		return sendConfirmData;
	}
	
}
