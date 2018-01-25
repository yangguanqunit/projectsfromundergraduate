package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class ForwardServer extends Thread {
	
	DatagramSocket serverSocket;
	DatagramPacket serverPacket;
	private String receivedMessage;
	private String targetUserName;
	private String targetAddress;
	private String contain;
	private String sourceUserName;
	private int targetPort;	
	private ConstructSendPacket constructSendPacket;
	
	private ArrayList<UserInformations> userInfoList;
	
	public ForwardServer(StoreInDatabase userList) throws IOException{
		
		this.serverSocket = new DatagramSocket(29771);
		this.serverPacket = new DatagramPacket(new byte[1024], 1024);
		this.constructSendPacket = new ConstructSendPacket();
		this.userInfoList = userList.userInfoList();
	}
	
	public void run(){
		while(true){
			 try {
				serverSocket.receive(serverPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			receivedMessage =new String(serverPacket.getData());
			//System.out.println("forwardtest");
			System.out.println(receivedMessage);
			targetUserName = receivedMessage.substring(0,16);                   //截取字符串获取发送方信息
			sourceUserName = receivedMessage.substring(16, 32);                 //获取接收方信息
			contain = receivedMessage.substring(32,receivedMessage.length());   //获取发送消息
			synchronized (userInfoList) {
				for(UserInformations userInfo : userInfoList){
					if(userInfo.getUserID().equals(targetUserName)){
						//System.out.println("Here is OK! ");
						System.out.println(userInfo.getUserID()+userInfo.state());
						if(userInfo.state()){
							try {
								userInfo.getDataSocket().send
        (userInfo.createSendSocket(constructSendPacket.sendPacket(sourceUserName, contain)));       //接收用户处于在线状态则转发内容
								
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						else                                //接收用户处于离线状态则存储离线消息，待用户上线后转发
						{
							userInfo.offlineMessage().add(constructSendPacket.sendPacket(sourceUserName, contain));
							userInfo.setOfflineMessage(true);
						}
						
					}
					
				}
			}
		}
			
			
	}
	

}
