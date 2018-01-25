package clientForMessageOperation;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConstructMessageBody {
	
	public String getLocalAddr() throws UnknownHostException{       //获取本机IP
		
		return "192.168.1.1";
	}
	
	public int getLocalPort(DatagramSocket localSocket){            //获取服务器IP
		
		
		return localSocket.getLocalPort();
	}
	public String messageBody(String ip,String port, String contain){
		
		return ip+port+contain;
		
	}

}
