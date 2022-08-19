package MYSQL20220816;

import java.sql.*;

public class Driverload5 {
// mysql commit 안하면 lock 걸려서 여기 실행안됨
	public static void main(String[] args) {
		String  url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String pw = "tiger";
		String sql = null;
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
			sql = "INSERT INTO BOOK VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 66);
			pstmt.setString(2, "행복한왕자");
			pstmt.setString(3, "와일드 출판사");
			pstmt.setInt(4, 12000);
			//수정
			pstmt.executeUpdate();
			
			sql = "SELECT * FROM BOOK WHERE BOOKID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,	66);
			
			//실행
			rs = pstmt.executeQuery();
			
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
