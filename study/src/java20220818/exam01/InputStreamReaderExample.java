package java20220818.exam01;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class InputStreamReaderExample {
// .txt 파일 저장언어변경한후 확인해보기
	public static void main(String[] args) {
		InputStreamReader is = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("e:/temp/test2.txt");
			is = new InputStreamReader(fis,"utf-8");
			int c;
			System.out.println("인코딩 문자집합은"+is.getEncoding());
			while ((c=is.read())!= -1) {
				System.out.print((char)c);
			}
			is.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
