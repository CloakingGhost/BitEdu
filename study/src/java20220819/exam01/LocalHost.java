package java20220819.exam01;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		InetAddress inetaddr = null;
		
		try { // cmd의 ipconfig와 같은 것
			inetaddr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(inetaddr.getHostName());
		System.out.println(inetaddr.getHostAddress());

	}

}
