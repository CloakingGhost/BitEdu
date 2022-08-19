package java20220819.exam05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatSever {

	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedReader stin = null;
		PrintWriter out = null;
		ServerSocket server = null;
		Socket socketOfServer = null;
		
		try {
			server = new ServerSocket(9999);
			socketOfServer = server.accept();
			System.out.println("연결됨");
			in = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
			stin = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
			String inputMessage;
			while(true) {
				inputMessage = in.readLine();
				if(inputMessage.equalsIgnoreCase("bye")) {
					break;
				}
				System.out.println("클라이언트> "+inputMessage);
				String outputMessage = stin.readLine();
				out.println("서버> "+ outputMessage);
				out.flush();
			}
			socketOfServer.close(); server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
