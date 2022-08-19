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
import java.util.StringTokenizer;

public class ServerExample1 {
	public static void main(String[] args) {
		Socket socketOfServer;
		PrintWriter pw;
		BufferedReader br;
		try {
			ServerSocket Server = new ServerSocket(9999);//포트 지정 및 개방
			System.out.println("접속을 기다리는중");
			socketOfServer = Server.accept();// 서버에 들어오는 것을 허락
			InetAddress inetaddr = socketOfServer.getInetAddress();//들어오는 아이피를 받는다
			System.out.println(inetaddr.getHostAddress());//
//			System.out.println(inetaddr);//
			
//		보내고 받고
			OutputStream out = socketOfServer.getOutputStream();//서버에서 나가는 통로
			InputStream in = socketOfServer.getInputStream();	//서버에 들어오는 통로
			pw = new PrintWriter(new OutputStreamWriter(out));
			br = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line=br.readLine()) != null) {
				System.out.println("클라이언트로 받은 문자열 : " + line);
				pw.println(calculate(line));//받은것을 다시 클라이언트에서 보낸다(버퍼에 저장)
				pw.flush();// 버퍼에 저장된 것을 스트림에 보낸다
				
			}
			pw.close();br.close();socketOfServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String calculate(String line) {
		StringTokenizer st = new StringTokenizer(line," ");
		String result = null;
//		int count;
//		if(st.countTokens())=! 3) {
//			return "연산식이 잘못됐습니다.";
//		}
		String[] arr = {st.nextToken(),st.nextToken(),st.nextToken()};
	
		switch(arr[1]) {
		case "+":
			result = String.format("%s = %.2f", line, Double.parseDouble(arr[0])+Double.parseDouble(arr[2]));
			break;
		case "-":
			result = String.format("%s = %.2f", line, Double.parseDouble(arr[0])-Double.parseDouble(arr[2]));
			break;
		case "x":
			result = String.format("%s = %.2f", line, Double.parseDouble(arr[0])*Double.parseDouble(arr[2]));
			
		case "*":
			result = String.format("%s = %.2f", line, Double.parseDouble(arr[0])*Double.parseDouble(arr[2]));
			break;
		case "/":
			result = String.format("%s = %.2f", line, Double.parseDouble(arr[0])/Double.parseDouble(arr[2]));
			break;
			default:
				result = "연산식이 잘못됐습니다.";
		}
		return result;
	}

}
