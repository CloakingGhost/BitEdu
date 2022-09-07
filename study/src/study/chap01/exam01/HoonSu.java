package study.chap01.exam01;

import java.io.IOException;

public class HoonSu {

	public static void main(String[] args) {
		int b ,len = 0;
		int bArray[] = new int[100];
		
		System.out.println("입력");
		try {
			while((b=System.in.read()) != '\n') {
				System.out.printf("%c(%d)",(char)b,b);
				bArray[len++] = b;
			}
			System.out.println("출력");
			for(int i =0;i<len;i++) {
				System.out.write(bArray[i]);
			}
			System.out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
