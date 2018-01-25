package server;
import java.io.*;
import java.net.*;

public class UserCheck_server {
	public boolean online=false;
	public String[] getData = new String[3];
//	private String confirmData;
//	private String UserId;
//	private String password;
	
	public void responseTCPRequest(StoreInDatabase VirtualDatabase) throws IOException{
		
		CreateSocket_server socketServerForCheckData = new CreateSocket_server(30000);          //服务器30000端口作为用户登陆验证端口
		
		
		while(true){
			OnlineCheck onlineCheck = new OnlineCheck();
			onlineCheck.setClientAcceptSocket(socketServerForCheckData.acceptClientRequest(),VirtualDatabase);
			onlineCheck.start();
		}
//		CreateSocket_server socketServerForCheckData = new CreateSocket_server(30000);
//		BufferedReader receiveConfirmRequest=new BufferedReader(new InputStreamReader(socketServerForCheckData.acceptClientRequest().getInputStream()));
//		PrintStream sendConfirmData = new PrintStream(socketServerForCheckData.acceptClientRequest().getOutputStream());
//		String confirmRequest = receiveConfirmRequest.readLine();
//		getData = confirmRequest.split(" ");
//		confirmData=getData[0];
//		UserId=getData[1];
//		password=getData[2];
//		System.out.println(confirmRequest);
//		judgeOnline JO = new judgeOnline(UserId, password);
//		if(confirmData.equals("CONFIRM")&&JO.confirmUserOnline()){
//			sendConfirmData.println("success");
//			this.online=true;
//		}
//		else{
//			sendConfirmData.println("filed.");
//			
//		}
//		sendConfirmData.close();
//		receiveConfirmRequest.close();
	}
	public void closeServerSocket(){
		
		
	}
	
	
	
		
}
