package java20220818.exam01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {

	public static void main(String[] args) throws IOException {
		InputStream is1 = new FileInputStream("e:/temp/test1.txt");
		int data;
		while((data=is1.read())!=-1) {
			System.out.println(data);
		}
		is1.close();
		
		InputStream is2 = new FileInputStream("e:/temp/test2.txt");
		byte[] buffer1 = new byte[100];
		int count1;
		while((count1 = is2.read(buffer1))!=-1) {
			for (int i =0; i<count1; i++) {
				System.out.println(buffer1[i]);
			}
		}
		InputStream is3 = new FileInputStream("e:/temp/test3.txt");
		byte[] buffer2 = new byte[5];
		int count2;
		while((count2 = is3.read(buffer2,2,3))!=-1) {
			for (int i =0; i<count2; i++) {
				System.out.println(buffer2[i]);
			}
		}
	}

}
