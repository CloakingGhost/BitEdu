package java20220818.exam02;

import java.io.File;

//file db network ui 필수
public class FileExample {
	public static void main(String[] args) {
		long start, end, duration;
		File file1 = new File("e:/temp/text4.txt");
		File file2 = new File("c:/windows/system.ini");
		String res;
		if (file2.isFile())
			res = "파일";
		else
			res = "디렉토리";
		System.out.println(file2.getPath() + " 은 " + res);
		
		start = System.nanoTime();
		File file3 = new File("c:/windows");
		File[] fs = file3.listFiles();
		for(File f :fs)
			if(f.isDirectory())
				System.out.printf("dir : %s\n",f);
			else
				System.out.printf("file : %s(%d bytes)\n",f,f.length());
		end = System.nanoTime();
		duration = end - start;
		System.out.println(duration);
	}
}
