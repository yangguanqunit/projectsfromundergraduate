package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

import tools.ConstructUDP;
import tools.StringToBinary;

public class UserInformations {                                             //用户信息存储格式
	
	private String userID;
	public String password;
	private int Port;
	private String address;
	private boolean statesOnline=false;
	private boolean offlineMessage=false;	
	private ArrayList<String> offlineBuffer;
	private DatagramSocket localSocket;
	private DatagramPacket localSendSocket;
	public UserInformations(String userId,String password) throws SocketException{
		StringToBinary stringToBinary_tool = new StringToBinary();
		this.userID = stringToBinary_tool.conpleteMessage(userId);
		this.password = stringToBinary_tool.conpleteMessage(password);
		localSocket = new DatagramSocket();
		this.offlineBuffer = new ArrayList<String>();
		//this.offlineBuffer.add("1234");
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setPort(int ipPort){
		this.Port = ipPort;
	}
	
	public void setOnline(){
		this.statesOnline = true;
	}
	
	public void offline(){
		this.statesOnline = false;
	}
	
	public boolean state(){
		
		return this.statesOnline;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public int port(){
		return this.Port;
	}
	
	public boolean offlinemessageJudge(){
		return offlineMessage;
	}
	public void setOfflineMessage(boolean newSet){
		this.offlineMessage = newSet;
	}
	
	public ArrayList<String> offlineMessage(){
		return this.offlineBuffer;
	}

	public String getUserID() {
		return userID;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public DatagramPacket createSendSocket(String message) throws  UnknownHostException{
		this.localSendSocket = new ConstructUDP(this.address, this.Port, message).UDPsocket();
		return this.localSendSocket;
	}
	public DatagramSocket getDataSocket(){
		return this.localSocket;
	}
	
	
	
}
