package MYSQL20220817.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProblemEmp1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String pw = "tiger";
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 드라이버 부르기
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 자바와 드라이버 연결(연결객체 생성)
			conn = DriverManager.getConnection(
					url,user,pw);
			//-------------------------------
			//준비
//			sql = "create table emp1 as" // as도 괄호가 필요함
//					+ " (select empno, ename, sal"
//					+ " from emp)"; 
//			sql = "drop table emp1 "; 
			sql = "select * from emp1 "; 
			// 테이블 생성완료 그 테이블로 emp2에서 새로 진행
			
			pstmt = conn.prepareStatement(sql);
			
			
			//실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				for(int i=1; i<4;i++) {
				System.out.print(rs.getString(i) + " ");
				}
				System.out.println();
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
