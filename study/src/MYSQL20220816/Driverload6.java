package MYSQL20220816;

import java.sql.*;

public class Driverload6 {
// mysql commit 안하면 lock 걸려서 여기 실행안됨
	public static void main(String[] args) {
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
			sql = "select job, avg(sal)\r\n"
					+ "from emp\r\n"
					+ "where job not in ('SALESMAN') and sal >=3000\r\n"
					+ "group by job;";
			pstmt = conn.prepareStatement(sql);
			
			//실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("job") + " " + rs.getInt("avg(sal)"));
			}
			
			
			
			
			
			conn.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
/*
 salesman을 제외하고, 각 업무별 사언의 총 급여가 3000 이상인 각 업무에 대해서, 업무명과 각 업무별 평균급여를 출력하시오.(scott)
 키보드에서 사원 이름 또는 성을 입력 받고 해당 사원과 같은 부서에서 이라는 사원 직원 정보 출력(hr)
 */ 
