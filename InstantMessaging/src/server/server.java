package server;
import java.io.*;
import java.net.*;

public class server {                                                           //服务器

	public static void main(String[] args) throws IOException{
		
		StoreInDatabase VirtualDatabase  = new StoreInDatabase();
		
		UserCheck_server userCheck_server = new UserCheck_server();
		ForwardServer forwardServer = new ForwardServer(VirtualDatabase );
		forwardServer.start();
		userCheck_server.responseTCPRequest(VirtualDatabase);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		UserCheck_server clientConnect = new UserCheck_server();
//		clientConnect.responseTCPRequest();
//		if(clientConnect.online)
//		{
//			System.out.println("client online");
//		}
//		
	}
}


