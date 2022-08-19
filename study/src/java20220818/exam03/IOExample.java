package java20220818.exam03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class IOExample {

	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader br = null;
		PrintWriter pw = null;
		String line = null;

		try {
			//콘솔에서 입력을 받아 지정 파일에 저장
			//콘솔에서 입력대기
			br = new BufferedReader(new InputStreamReader(System.in));// 콘솔 입력
			//파일을 읽어 지정 파일에 저장(복사)
			//파일을 읽음
//			br = new BufferedReader(new FileReader("e:/temp/dorian.txt")); // 파일 입력
//			pw = new PrintWriter(new FileWriter("e:/temp/test3.txt"));
			while ((line = br.readLine()) != null) {
				System.out.println(line);// 한줄씩 읽는다 콘솔 출력
//				pw.println(line);// 파일에 입력
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
