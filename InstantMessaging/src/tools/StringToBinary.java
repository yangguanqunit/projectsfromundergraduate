package tools;

public class StringToBinary {

//	private StringBuffer binaryIp = new StringBuffer(); 
	
	
	
                                                                
	
	public String shiftForIP(String preparedToShift){
		StringBuffer binaryIp = new StringBuffer();
		int i=0;
		String[] tempToStor = preparedToShift.split("\\.");
		for(String divide:tempToStor){
			int length;
			String tempString = Integer.toBinaryString(Integer.parseInt(divide));
			if((length =tempString.length())!=8){
				while(length++<8){
					tempString = "0"+tempString;
				}
			}
			binaryIp.append(tempString);
		}	
		
		return binaryIp.toString();
	}
	public String shiftForPort(int preparedToShift){
		int length;
		String tempToStor;
		tempToStor=Integer.toBinaryString(preparedToShift);
		if((length=tempToStor.length())!=32){
			
			while(length++<32)
				tempToStor = "0"+tempToStor;
		}
		return tempToStor;
	}
	public String conpleteMessage(String preMessage){
		
		String temp = preMessage;
		if(temp.length()>16){
			System.out.println("too long");
		}
		else
		{
			while(temp.length()<16){
				temp = temp+"0";
			}
		}
		return temp;
		
	}
	
	
}
