package java20220819.exam02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWebSite {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://en.wikipedia.org");
			BufferedReader in = 
					new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while((inputLine = in.readLine())!=null) {
				System.out.println(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
