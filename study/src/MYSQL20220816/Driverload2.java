package MYSQL20220816;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Driverload2 {

	public static void main(String[] args) {
		String  url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String pw = "tiger";
//		String sql1 = String.format("select * from book where bookname = %s", "야구의 추억");
		try { // 드라이버 로딩 ->
			// 드라이버 부르기
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 자바와 드라이버 연결(연결객체 생성)
			Connection conn = DriverManager.getConnection(
					url,user,pw);
			//Statement 객체 생성
			Statement stmt = conn.createStatement();
			
			//데이터를 새로 추가한 다음 조회
			String sql3 = String.format("UPDATE BOOK SET BOOKNAME='%s' WHERE BOOKID = %d", "CCCC",100);
			int rows = stmt.executeUpdate(sql3);
			System.out.printf("%d의 행이 변경됨\n",rows);
//			String sql3 = String.format("INSERT INTO BOOK VALUES(%d, '%s', '%s', %d)", 79, "하디보일드원더랜드", "하루끼", 14000);
//			int rows = stmt.executeUpdate(sql3);
//			System.out.printf("%d의 행이 입력됨\n",rows);
//			ResultSet rs2 = stmt.executeQuery(sql1);
			ResultSet rs1 = stmt.executeQuery("select * from book");
			while(rs1.next()) {
			System.out.println(rs1.getInt("BookId") + " " + rs1.getString("BookName")
			+ " " + rs1.getString("publisher") + " " + rs1.getInt("price"));}
//			while(rs2.next()) {
//				System.out.println(rs2.getInt("BookId") + " " + rs2.getString("BookName")
//				+ " " + rs2.getString("publisher") + " " + rs2.getInt("price"));
//			}
			conn.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
