package java20220819.exam04;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {

	public static void main(String[] args) {
		try {
			Socket socketOfClient = new Socket("127.0.0.1",9999);
			OutputStream out = socketOfClient.getOutputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			pw.println("Hi.Server");
			pw.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
