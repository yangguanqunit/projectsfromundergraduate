package socketTest;
import java.io.IOException;
import java.lang.*;
import java.math.BigInteger;
import java.net.*;

import tools.BinayToStringOrInt;
import tools.ShiftTagetIpToSourceIp;
import tools.SplitInfrontOf96;
import tools.StringIpToRealIp;
import tools.StringToBinary;

public class SocketTest {

	public static void main(String[] args) throws Exception {
//		String ip = "127.0.0.1";
//		String[] ipB = new String[4];
//		Integer a=new Integer(0);
//		int length = 0;
//		String[] ipl = ip.split("\\.");
//		int i = 0;
//		String binary;
//		for(String test:ipl){
//			binary = a.toBinaryString(Integer.parseInt(test));
//			if((length=binary.length())!=8){
//				while(length++<8){
//					
//					binary="0"+binary;
//				}
//				System.out.println(binary);
//				System.out.println(Integer.valueOf(binary, 2));
//			}
//		}
//		for(String bin:ipB){
//			
//			System.out.println(bin);
//		}
		
//		StringToBinary a = new StringToBinary();
//		byte[] bytes = new byte[4];
//		//System.out.println(a.shiftForIP().length());
//		InetAddress t = InetAddress.getByName("127.0.0.1");
//		System.out.println(t);
//		System.out.println(Integer.valueOf(a.shiftForPort("9000"), 2));
//		String c= a.shiftForIP("127.0.0.1");
//		System.out.println(new BinayToStringOrInt().binaryPortToInt(a.shiftForPort("9000")));
//		String q = a.shiftForIP("127.0.0.1")+" "+a.shiftForPort("9000")+"1111";
//		SplitInfrontOf64 splitHead = new SplitInfrontOf64();
//		String[] gg = splitHead.splitMessage(q);
//		System.out.println(a.shiftForIP("127.0.0.1"));
//		System.out.println(gg[0]+" "+gg[1]+" ");
//		String b = c.substring(0,8);
//		System.out.println(b);
//		System.out.println(c.substring(8,16));
//		System.out.println(c.substring(16,24));
//		System.out.println(c.substring(24,32));		
//		StringToBinary b = new StringToBinary();
//		String a = b.shiftForIP("127.0.0.1") + b.shiftForPort(9000) + "你好啊";
//		String[] c = new SplitInfrontOf64().splitMessage(a);
//		System.out.println(c[0]+"|||"+c[1]+"|||"+c[2]);
//		System.out.println(new ShiftTagetIpToSourceIp().shiftToSourceIP(new StringToBinary().shiftForIP("127.0.0.1"), "192.168.1.1"));
		DatagramSocket socket = new DatagramSocket();
		System.out.println(socket.getLocalPort());
	}
}
