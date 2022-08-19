package MYSQL20220816;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Driverload1 {

	public static void main(String[] args) {
		
		try { // 드라이버 로딩 ->
			// 드라이버 부르기
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 자바와 드라이버 연결(연결객체 생성)
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/scottdb?useSSL=false","scott","tiger");
			//Statement 객체 생성
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book");
//			ResultSet rs = stmt.executeQuery("select bookid from book");
//			ResultSet rs = stmt.executeQuery("select bookid from book where bookid=1");
			rs.next();
			System.out.println(rs.getInt("BookId")
													  + " " + rs.getString("BookName") + " " +
													  rs.getString("publisher") + " " + rs.getInt("price")
													 );
			rs.next();
			System.out.println(rs.getInt(1) + " " + rs.getString(2)
			+ " " + rs.getString(3) + " " + rs.getInt(4));
			conn.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
