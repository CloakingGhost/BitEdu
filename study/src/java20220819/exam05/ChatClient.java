package java20220819.exam05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedReader stin = null;
		PrintWriter out = null;
		Socket socketOfClient = null;

		try {
			socketOfClient = new Socket("localhost",9999);
			in = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
			stin = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
			String outputMessage;
			while(true) {
				outputMessage = stin.readLine();
				if(outputMessage.equals("bye")) {
					out.println(outputMessage); out.flush();break;
				}
				out.println("클라이언트> "+outputMessage);
				String inputMessage = in.readLine();
				System.out.println(inputMessage);
			}
			socketOfClient.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
