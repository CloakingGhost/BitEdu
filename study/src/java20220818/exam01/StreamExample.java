package java20220818.exam01;

import java.io.IOException;

public class StreamExample {

	public static void main(String[] args) throws IOException {
		int b, len = 0;
		int bArray[] = new int[100];
		
		System.out.println("----입력스트림");
		while((b = System.in.read()) != '\n') {
			System.out.printf("%c(%d)",(char) b,b);
			bArray[len++] = b;
		}
		System.out.println("----출력스트림");
		for(int i=0;i<len; i++) {
			System.out.write(bArray[i]);//출력을 저장해놓은 버퍼에 저장중
		}	
		System.out.flush();
		
		System.out.close();
		
	}

}
