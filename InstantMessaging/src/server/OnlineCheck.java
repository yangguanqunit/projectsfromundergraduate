package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.*;
import java.net.Socket;
import java.util.*;
import javax.swing.text.html.HTMLDocument.Iterator;

import tools.BinayToStringOrInt;
public class OnlineCheck extends Thread {
	
	private BufferedReader bufferedReaderForAcceptClient;
	private Socket clientAcceptSocket;
	private String messageFromUser;
	private String userName;
	private String password;
	private String address;
	private int port;
	private boolean notExist=true;
	private StoreInDatabase storeInDatabase;
	public void setClientAcceptSocket(Socket clientAcceptSocket , StoreInDatabase storeInDatabase){     //建立用户登陆端口
		this.clientAcceptSocket = clientAcceptSocket;
		this.storeInDatabase  = storeInDatabase;
	}
	public void run(){
		try{
			
			ArrayList<UserInformations> userList = storeInDatabase.userInfoList();          //用户登陆信息验证
			BinayToStringOrInt analysisRequest = new BinayToStringOrInt();
			bufferedReaderForAcceptClient = new BufferedReader(new InputStreamReader(clientAcceptSocket.getInputStream()));
			messageFromUser = bufferedReaderForAcceptClient.readLine();
			System.out.println(messageFromUser+messageFromUser.length());
			address = analysisRequest.binaryIpToString(messageFromUser.substring(0, 32));
			port = analysisRequest.binaryPortToInt(messageFromUser.substring(32, 64));
			userName = messageFromUser.substring(64, 80);
			password = messageFromUser.substring(80, 96);
			PrintStream TCPMessage = new PrintStream(clientAcceptSocket.getOutputStream());
			synchronized (userList) {
				for(UserInformations userData : userList){
					if(userData.getUserID().equals(userName)&&userData.getPassword().equals(password)){
						userData.setAddress(address);
						userData.setPort(port);
						userData.setOnline();
						userData.setOfflineMessage(true);
						TCPMessage.println("SUCCESS");
						System.out.println(userData.getUserID()+userData.state());
						ArrayList<String> offlineMessageBuffer =userData.offlineMessage();
						if(userData.offlinemessageJudge()){
							//System.out.println("abc");
							java.util.Iterator<String> iter = offlineMessageBuffer.iterator();
							while(iter.hasNext()){
								TCPMessage.println(iter.next());
							}
							userData.setOfflineMessage(false);
						}
						notExist=false;
						break;
					}
				}		
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			PrintStream TCPMessage;
			try {
				TCPMessage = new PrintStream(clientAcceptSocket.getOutputStream());
				if(notExist)
				{
					TCPMessage.println("FILED");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clientAcceptSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
