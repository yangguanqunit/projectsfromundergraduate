package client;

import java.io.*;
import java.net.*;

import com.sun.xml.internal.ws.resources.SenderMessages;

public class CheckForTCP_cliet {

    public boolean isSuccessed = false;
    static String checkData_success = "SUCCESS";
    static String checkData_fail = "FAIL";
    private String confirmMessage;
    private Socket client_socket;
    private PrintStream client_request;
    private BufferedReader client_receive;
    private String receivedMessage;
    private String offlineMessage;
    private ReceiveMessage_client receiveMessage_client;

    public CheckForTCP_cliet() throws IOException {
        CreateSocket_client createSocket_client = new CreateSocket_client();  //构造方法初始化建立TCP连接验证用户账号密码
        this.client_socket = createSocket_client.getClientSocket();
        this.client_request = createSocket_client.getPrintStream();
        this.client_receive = createSocket_client.getBufferedRead();
    }

    public void creatConnect() throws IOException {
        ConstructConfirmPacket sendConfirm = new ConstructConfirmPacket(); 
        confirmMessage = sendConfirm.constructConfirmPacket(client_socket);  //构造用户信息串
        client_request.println(confirmMessage);
        receivedMessage = client_receive.readLine();
        while (!isSuccessed) {                                                  
            if (receivedMessage.equals(checkData_success)) {                    //用户信息匹配成功开启UDP信息传送线程
                System.out.println(checkData_success);
                while ((offlineMessage = client_receive.readLine()) != null) {
                    System.out.println(offlineMessage);
                }
                isSuccessed = true;
                SendMessage sendMessage = new SendMessage(sendConfirm.sendClientSocket());
                this.receiveMessage_client = new ReceiveMessage_client(sendConfirm.sendClientSocket());
                sendMessage.start();
                receiveMessage_client.start();
            } else {
                System.out.println(checkData_fail);                             //用户信息匹配失败 重新建立TCP连接
                 this.client_receive.close();
                this.client_request.close();
                this.client_socket.close();
                CreateSocket_client createSocket_client = new CreateSocket_client();
                this.client_socket = createSocket_client.getClientSocket();
                this.client_request = createSocket_client.getPrintStream();
                this.client_receive = createSocket_client.getBufferedRead();
                confirmMessage = sendConfirm.constructConfirmPacket(client_socket);
                client_request.println(confirmMessage);
                receivedMessage = client_receive.readLine();
            }
        }

//		CreateSocket_client socketForCheckData=new CreateSocket_client(30000);
//		checkData=checkData+" user";
//		checkData=checkData+" password";
//		
//
//		new SendMessage();
//		SendMessage.sendData(socketForCheckData.getPrintStream(), checkData);
//		String ConfirmResponse=socketForCheckData.getBufferedRead().readLine();
//		if(ConfirmResponse.equals("success")){
//			this.isSuccessed=true;
//			System.out.println("Successed");
//		}
//		else{
//			System.out.println("Maybe user or password is worng.");
//			socketForCheckData.getClientSocket().close();
//		}
//		socketForCheckData.getBufferedRead().close();
//		socketForCheckData.getPrintStream().close();
    }
}
