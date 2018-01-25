package tools;

public class BinayToStringOrInt {                           //工具类 二进制IP转化为点分十进制IP
	private int ipLength=32;
	private String stringIp="";
	private int port;
	
	public String binaryIpToString(String binaryString){
		int eachEightBit=0;
		while(true){
			stringIp = stringIp + Integer.toString(Integer.valueOf(binaryString.substring(eachEightBit, eachEightBit=eachEightBit+8), 2));
			if(eachEightBit==ipLength){
				break;
			}
			stringIp = stringIp + ".";
		}
		return stringIp;
	}
	
	public int binaryPortToInt(String binaryInt){
		
		
		
		return Integer.valueOf(binaryInt,2);
		
		
		
	}
	
}
