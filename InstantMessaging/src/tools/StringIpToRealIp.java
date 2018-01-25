package tools;

import java.net.InetAddress;
import java.net.UnknownHostException;                                           

public class StringIpToRealIp {                                                 //获取格式化IP
	

	public InetAddress shiftToInetaddr(String stringIP) throws UnknownHostException{
		InetAddress Inetaddr = InetAddress.getByName(stringIP);
		return Inetaddr;
		
		
	}
}
