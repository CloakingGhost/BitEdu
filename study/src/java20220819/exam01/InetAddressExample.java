package java20220819.exam01;

import java.net.InetAddress;
import java.net.UnknownHostException;
//cmd에서 사용한 nslookup과 같음
public class InetAddressExample {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("IP주소나 도메인 주소를 인자로 지정하세요");
			System.exit(0);
		}
		InetAddress inetAddr[] = null;
		InetAddress ia = null;
		
		try {
			ia = InetAddress.getByName(args[0]); //한 개만
			inetAddr = InetAddress.getAllByName(args[0]);//전부
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(ia.getHostName()); //한 개만
		System.out.println(ia.getHostAddress());
		
		
		System.out.println("==================");
		for(int i =0; i<inetAddr.length;i++) { //전부
			System.out.println(inetAddr[i].getHostName());
			System.out.println(inetAddr[i].getHostAddress());
			System.out.println(inetAddr[i].toString());
			System.out.println("==================");
		}
		
	}

}
