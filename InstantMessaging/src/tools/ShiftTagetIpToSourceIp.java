package tools;

public class ShiftTagetIpToSourceIp {                   //源目标地址转化(发送方——>服务器-->接收方)
	StringToBinary shiftTool;
	
	
	public ShiftTagetIpToSourceIp(){
		
		this.shiftTool = new StringToBinary();
		
	}
	
	public String shiftToSourceIP(String sourceMessage,String sourceIp){
		StringBuffer buffer = new StringBuffer(sourceMessage);
		
		buffer.replace(0, 64, shiftTool.shiftForIP(sourceIp));
		return  buffer.toString();
	}
	
}
