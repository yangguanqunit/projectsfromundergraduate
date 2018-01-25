package socketTest;
import java.io.*;
import java.net.*;

public class ClientForSocketTest {

	public static void main(String[] args) throws IOException {
		Socket socket=new Socket("127.0.0.1",30000);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps=new PrintStream(socket.getOutputStream());
		String line =buffer.readLine();
		System.out.println("来自服务器："+line);
		ps.println("hello,data from client sent successfully.");
		ps.println("bye");
		ps.close();
		buffer.close();
		socket.close();
	}
}
