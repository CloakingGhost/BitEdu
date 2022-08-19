package java20220818.exam01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {

	public static void main(String[] args) throws FileNotFoundException {
		OutputStream os = new FileOutputStream("E:/temp/test1.txt");
		try {
			os.write((byte)10);
			os.write((byte)20);
			os.write((byte)30);
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
