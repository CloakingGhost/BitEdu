package java20220819.exam04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		Socket socketOfServer;
		PrintWriter pw;
		BufferedReader br;
		try {
			ServerSocket server = new ServerSocket(9999);
			System.out.println("접속을 기다립니다.");
			socketOfServer = server.accept();
			InetAddress inetaddr = socketOfServer.getInetAddress();
			System.out.println(inetaddr.getHostAddress()+"로 부터 접속하였습니다.");
			OutputStream out = socketOfServer.getOutputStream();//서버에서 나가는 통로
			InputStream in = socketOfServer.getInputStream();	//서버에 들어오는 통로
			pw = new PrintWriter(new OutputStreamWriter(out));
			br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while((line = br.readLine()) != null) {
				System.out.println("클라이언트로 부터 전송 받은 문자열:" + line);
				pw.println(line);
				pw.flush();
			}
			pw.close();br.close();socketOfServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
}
