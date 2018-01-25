package client;

import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import tools.StringToBinary;

public class ConstructConfirmPacket {                       //构造用户信息串
	
	private String userMessage;
	private DatagramSocket applyForPort;
	private String userName;
	private String password;
	private int port;
	private String localhostAddress;
	private StringToBinary stringToBinary_tool;
	private Scanner inputUserMessage;
	public ConstructConfirmPacket() throws SocketException{
		this.applyForPort = new DatagramSocket();
		this.stringToBinary_tool = new StringToBinary();
		this.userMessage = "";
		this.inputUserMessage = new Scanner(System.in);
//		this.userName="";
//		this.localhostAddress = "";
	}
	
	public String constructConfirmPacket(Socket socket){                    //将用户账号密码IP端口等信息以固定格式存储以便服务器端解析
		userName = inputUserMessage.nextLine();                         //输入用户名
		password = inputUserMessage.nextLine();                         //密码
		localhostAddress=socket.getInetAddress().getHostAddress();
		port = applyForPort.getLocalPort();
		userMessage = stringToBinary_tool.shiftForIP(localhostAddress);
		userMessage = userMessage + stringToBinary_tool.shiftForPort(port);
		userMessage = userMessage + stringToBinary_tool.conpleteMessage(userName)+stringToBinary_tool.conpleteMessage(password);
		return userMessage;
	}
	
	public DatagramSocket sendClientSocket(){
		return this.applyForPort;
	}

}
