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
import java.text.SimpleDateFormat;
import java.util.Date;

public class EchoClient {

	public static void main(String[] args) {
		try {
			Socket socketOfClient = new Socket("127.0.0.1",9999);
			BufferedReader keyboard
			= new BufferedReader(new InputStreamReader(System.in));
			OutputStream out = socketOfClient.getOutputStream();//클라이언트에서 나가는 통로
			InputStream in = socketOfClient.getInputStream();	//클라이언트에 들어오는 통로
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			Date date = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowDate = sdf1.format(date);
			while((line = keyboard.readLine()) != null) {
				if(line.equals("quit")) {break;}
				pw.println(line);pw.flush();
				String echo = br.readLine();
				System.out.print(nowDate);
				System.out.println(" 서버로부터 전달받은 문자열:"+echo);
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
