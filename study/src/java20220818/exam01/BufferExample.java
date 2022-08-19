package java20220818.exam01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferExample {

	public static void main(String[] args) {
		long start, end, duration;
		String org = "c:/Program Files (x86)/Internet Explorer/iexplore.exe";
		String dst = "e:/temp/iexplore1.exe";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		start = System.nanoTime();

		try {//이 방식 또는 Scanner를 사용해라
			bis = new BufferedInputStream(new FileInputStream(org));
			bos = new BufferedOutputStream(new FileOutputStream(dst));
			while (bis.available() > 0) {
				int b = bis.read();
				bos.write(b);
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		end = System.nanoTime();
		duration = end - start;
		System.out.println(duration);

		start = System.nanoTime();
		try {
			fis = new FileInputStream(org);
			fos = new FileOutputStream(dst);
			while (fis.available() > 0) {
				int b = fis.read();
				fos.write(b);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		end = System.nanoTime();
		duration = end - start;
		System.out.println(duration);

	}

}
