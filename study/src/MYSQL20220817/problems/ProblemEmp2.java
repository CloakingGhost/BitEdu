package MYSQL20220817.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProblemEmp2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String pw = "tiger";
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
//			// 드라이버 부르기
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			// 자바와 드라이버 연결(연결객체 생성)
			conn = DriverManager.getConnection(
					url,user,pw);
			// -------------------------------
			// 준비
			do {
				System.out.println("사번,이름,월급 순서로 입력하시오");
				sql = "insert into emp1 values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sc.nextInt());
				pstmt.setString(2, sc.next());
				pstmt.setInt(3, sc.nextInt());
				pstmt.executeUpdate();
				System.out.println("정보가 추가되었습니다." + "\n입력을 계속 하시겠습니까?(y/n)");
			} while (check(sc.next()));

			// 실행
//			rs = pstmt.executeQuery();

//			while(rs.next()) {
//				for(int i=1; i<4;i++) {
//				System.out.println(rs.getString(i));
//				}
//			}
			conn.close();
		} catch (Exception e) {
			System.out.println("다시입력해주세요");
		}
	}

	static boolean check(String check) {
		if (check.equals("y")) {
			return true;
		} else if (check.equals("n")) {
			return false;
		} else {
			System.out.println("다시 입력해주세요");
		}
		return true;

	}
}
