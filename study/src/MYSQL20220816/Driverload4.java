package MYSQL20220816;

import java.sql.*;

public class Driverload4 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String pw = "tiger";
		String sql1 = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { // 드라이버 로딩 ->
				// 드라이버 부르기
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 자바와 드라이버 연결(연결객체 생성)
			conn = DriverManager.getConnection(url, user, pw);
			// 준비
//			sql1 = "select * from book where bookid in(?,?)";
			sql1 = "select * from book where bookid in(?,?,?,?)";
			// prepareStatement 객체 생성
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, 2);// 1번 물음표에 bookid 2입력
			pstmt.setInt(2, 4);// 2번 물음표에 bookid 4입력
			pstmt.setString(3, "aaa");// 3번 물음표에 "aaa" 입력:출력안됨
			pstmt.setInt(4, 8);// 4번 물음표에 bookid 8입력
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("BookId") + " " + rs.getString("BookName") + " "
						+ rs.getString("publisher") + " " + rs.getInt("price"));
			}

			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
