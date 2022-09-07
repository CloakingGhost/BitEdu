package study.chap01.exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ggo1 {

	public static void main(String[] args) {
		String word = "감사";

		String url = "jdbc:mysql://localhost:3306/newword?useSSL=false&allowPublicKeyRetrieval=true";
		String dbid = "newword";
		String dbpw = "123456";
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbid, dbpw);
			System.out.print("dd");
			sql = "select * from newword where word = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, word);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString(2));

			}
		} catch (Exception e) {
			System.out.print("ss");
			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
