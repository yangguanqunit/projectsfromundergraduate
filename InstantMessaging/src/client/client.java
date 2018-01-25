package client;
import java.io.*;
import java.util.*;
import java.net.*;

public class client {
	
	
	
	public static void main(String[] args)throws IOException {                          //客户端
		
		

		CheckForTCP_cliet requestServer = new CheckForTCP_cliet();
		requestServer.creatConnect();
//		requestServer.creatConnect();
//		if(requestServer.isSuccessed){
//			System.out.println("request successfully");
//		}	
	}
}
