package MYSQL20220816;

import java.sql.*;

public class Driverload3 {

	public static void main(String[] args) {
		String  url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
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
			conn = DriverManager.getConnection(
					url,user,pw);
			//준비
			sql1 = "select * from book";
			//prepareStatement 객체 생성
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery(sql1);
			while(rs.next()) {
				System.out.println(rs.getInt("BookId") + " " + rs.getString("BookName")
				+ " " + rs.getString("publisher") + " " + rs.getInt("price"));
			}
			
			
			
			
			
			conn.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
