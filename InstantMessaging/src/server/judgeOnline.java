package server;
import java.util.*;
import java.lang.*;

public class judgeOnline {
	
	
	static Map<String,String> mapForUP = new HashMap();                 //用户信息匹配方法
	private boolean userAndPasswordIsCorrect=false;
	
	public  judgeOnline(String user,String password){               
		this.mapForUP.put("user", "password");
		for(String key : this.mapForUP.keySet()){
			if(user.equals(key)){
				if(mapForUP.get(key).equals(password)){
					this.userAndPasswordIsCorrect=true;
				}
				break;
			}
			
		}
		
	}
	
	public boolean confirmUserOnline(){
		return this.userAndPasswordIsCorrect;
	}
	
}
