package MYSQL20220816;

import java.sql.*;
import java.util.Scanner;

public class Driverload7 {
// mysql commit 안하면 lock 걸려서 여기 실행안됨
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/hr?useSSL=false";
		String user = "hr";
		String pw = "hr";
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = sc.next();
		try { // 드라이버 로딩 ->
				// 드라이버 부르기
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 자바와 드라이버 연결(연결객체 생성)
			conn = DriverManager.getConnection(url, user, pw);
			// -------------------------------
			// 준비
			sql = "select *\r\n" + "from employees\r\n" + "where department_id = (select department_id\r\n"
					+ "						  from employees\r\n"
					+ "                       where first_name like ? or last_name like ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			// 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {

				for (int i = 1; i <= 11; i++) {
					System.out.print(rs.getString(i) + " ");
				}
				System.out.println();
			}

			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
/*
 * salesman을 제외하고, 각 업무별 사언의 총 급여가 3000 이상인 각 업무에 대해서, 업무명과 각 업무별 평균급여를
 * 출력하시오.(scott) 키보드에서 사원 이름 또는 성을 입력 받고 해당 사원과 같은 부서에서 이라는 사원 직원 정보 출력(hr)
 */
