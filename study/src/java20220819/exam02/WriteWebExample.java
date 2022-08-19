package java20220819.exam02;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
//어떻게 하는지 전혀모르겠음
public class WriteWebExample {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://en.wikipedia.org");
			FileWriter fw = new FileWriter("e:/temp/indexWK.html");
			BufferedReader in = 
					new BufferedReader(new InputStreamReader(url.openStream()));
			
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				fw.write(inputLine);
			}
			fw.close();in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
