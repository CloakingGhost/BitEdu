package java20220818.exam01;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileWriterExample {

	public static void main(String[] args) {
		// Scanner 대신 사용가능
		// 키보드에 입력받은 내용을 파일에 저장 enter,ctrl+z 누르면 종료
		InputStreamReader in = new InputStreamReader(System.in);
		FileWriter fout = null;
		int c;
		try {
			fout = new FileWriter("e:/temp/test1.txt");
			while((c=in.read())!=-1) {
				fout.write(c);
			}
			in.close();
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("입출력오류");
		}
	}

}
