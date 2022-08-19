package java20220819.exam04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(9999);//소켓 포트번호
			System.out.println("접속을 기다립니다.");
			Socket socketOfServer = server.accept();
			InputStream in = socketOfServer.getInputStream(); // 소켓에 입력스트림
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();//소켓에 연결된 클라이언트 입력
			System.out.println("받은 문자열 = " + line); // 콘솔 출력
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
