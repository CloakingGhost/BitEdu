package java20220819.exam04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExample1 {

	public static void main(String[] args) {
		try {
			Socket socketOfClient = new Socket("127.0.0.1",9999);//아이피와 포트 지정
			BufferedReader keyborad
			= new BufferedReader(new InputStreamReader(System.in));
			OutputStream out = socketOfClient.getOutputStream();
			InputStream in = socketOfClient.getInputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));//서버로 문자열을 바이트로 바꿈
			BufferedReader br = new BufferedReader(new InputStreamReader(in));//서버가 보내준내용을 유니코드 문자열로 바꿈
			String line;
			
			while((line = keyborad.readLine()) != null) {
				if(line.equals("quit")) {
					break;
				}
				pw.println(line); pw.flush();//키보드로 받고 / 버퍼에 있는 것을 내보냄
				String echo = br.readLine();//서버에서 보낸것을 받음
				System.out.println("서버로부터 전달받은 문자열 : " + echo);
			}
			pw.close();br.close();socketOfClient.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
