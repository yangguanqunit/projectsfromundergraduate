package tools;

public class SplitInfrontOf96 {                             //用户登陆信息格式化96位
	private static int headIP = 32;
	private static int headPort = headIP+32;
	private String[] processed = new String[4];
	private String[] contain = new String[3];
	
	public String[] splitMessage(String preMessage){
		
		processed[0]=preMessage.substring(0, headIP);
		processed[1]=preMessage.substring(headIP,headPort);
		processed[2]=preMessage.substring(headPort, headPort+16);
		processed[3]=preMessage.substring(headPort+16,preMessage.length());
		return processed;
	}
	
	public String[] splitUDP(String message){
		contain[0] = message.substring(0,16);
		contain[1] = message.substring(16,32);
		contain[2] = message.substring(32,message.length());
		return contain;
	}
	

}
