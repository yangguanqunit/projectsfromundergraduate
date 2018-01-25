package client;

import tools.StringToBinary;
import java.util.*;

public class InputContain {                                 //发送信息格式化
	
	
	private String targetName;
	private String sourceName;
	private StringToBinary stringToBinary_tool;
	
	
	public InputContain(String targetName,String sourceName){
		
		this.targetName = targetName;
		this.sourceName = sourceName;
		this.stringToBinary_tool = new StringToBinary();
		
	}
	public String sendPacket(String message){
		
		message = stringToBinary_tool.conpleteMessage(this.sourceName) + message;
		message = stringToBinary_tool.conpleteMessage(this.targetName) + message;	
		return message;
	}
}
