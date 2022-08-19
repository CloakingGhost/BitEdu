package java20220818.exam03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IOProblem {

	public static void main(String[] args) throws IOException {
//		콘솔에서 입력된 단어가 파일에서 몇 번 사용됐는지 출력
//		무한반복 프로그램이며 강제종료시켜야함
//		단어의 기준 [., '"]로 구분되는 토큰
//		대소문자 구분 없음
//		단수 복수 단어는 다른단어

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader file = new BufferedReader(new FileReader("e:/temp/dorian.txt"));
		String brWord = null;
		String line = null;
		StringTokenizer st = null;
		List<String> al = new ArrayList<String>();

		try {// 파일 부르기
			while ((line = file.readLine()) != null) {
				st = new StringTokenizer(line, "., '\"");
				while (st.hasMoreTokens()) {
					al.add(st.nextToken());
				}
			}

			while (true) {
				int findWord = 0;
				System.out.print("찾고싶은 단어가 무엇입니까?");
				brWord = br.readLine();
				for (String al1 : al) {
					if (al1.equalsIgnoreCase(brWord)) {
						findWord++;
					}
				}
				if (findWord != 0) {
					System.out.printf("%d번 사용됨\n", findWord);
				} else {
					System.out.println("한번도 사용한적 없음");
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
