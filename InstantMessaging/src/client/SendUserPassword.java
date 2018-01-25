package client;

import java.io.PrintStream;

public class SendUserPassword implements SendDataInterface {
	
	private String userID;
	private String password;
	public SendUserPassword(String UserId,String Password){                 //构建用户名密码发送包
		this.userID = UserId;
		this.password = Password;
	}
	
	public void sendData(PrintStream readyToSend,String message){           //test
		readyToSend.println(this.userID);
		readyToSend.println(password);	
	}
	
	

}
