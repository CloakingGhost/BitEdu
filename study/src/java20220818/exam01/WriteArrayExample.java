package java20220818.exam01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteArrayExample {

	public static void main(String[] args) throws IOException {
		OutputStream os1 = new FileOutputStream("e:/temp/test2.txt");
		byte[] bArray1 = {10,20,30};
		os1.write(bArray1);
		os1.flush();
		os1.close();
		OutputStream os2 = new FileOutputStream("e:/temp/test3.txt");
		byte[] bArray2 = {10,20,30,40,50};
		os2.write(bArray2,1,3);
		os2.flush();
		os2.close();
	}

}
