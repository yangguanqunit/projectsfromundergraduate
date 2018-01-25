package server;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

public  class StoreInDatabase {
	 ArrayList<UserInformations> userList ;                                     //用户信息存储，可用数据库替换
	
	public StoreInDatabase()throws IOException{
		this.userList = new ArrayList<UserInformations>();
		this.userList.add(new UserInformations("yang", "123456"));
		this.userList.add(new UserInformations("ygq", "abcdef"));
		System.out.println(userList.size());
		System.out.println(userList.get(1).getUserID());
		System.out.println(userList.get(1).getPassword());
	}
	
	public  ArrayList<UserInformations> userInfoList(){
		return userList;
	}

}
