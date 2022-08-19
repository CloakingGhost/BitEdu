package MYSQL20220817.problems.memders1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

//auto_increment is in mysql

public class ProblemMembersExample {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String pw = "tiger";
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);

		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			// 자바와 드라이버 연결(연결객체 생성)
			conn = DriverManager.getConnection(url, user, pw);
			while (true) {
				System.out.println("1.추가 2.조회 3.수정 4.삭제 5.모두보기 6.종료\n메뉴를 선택하시오 >>");
				switch (sc.nextInt()) {
				case 1:
					System.out.println("이름 비밀번호 이메일 전화번호 순으로 입력하시오");
					// 준비
					try {
						sql = "INSERT INTO members(userName,userPw,email,phoneNo) VALUES(?,?,?,?)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, sc.next());
						pstmt.setString(2, sc.next());
						pstmt.setString(3, sc.next());
						pstmt.setString(4, sc.next());
						// 실행
						pstmt.executeUpdate();
						// 종료
					} catch (Exception e1) {
						System.out.println("각 프로그램의 연결상태를 확인해주세요");
					}
					break;
				case 2:
					System.out.print("검색할 회원의 이름을 입력하시오>>");
					try {
						sql = "select * from members where userName = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, sc.next());
						rs = pstmt.executeQuery();
						if (rs.next()) {
							ProblemMembers m = new ProblemMembers(rs.getInt(1), rs.getString(2), rs.getString(3),
									rs.getString(4), rs.getString(5), rs.getString(6));
							System.out.println(m);	
						}else {
							System.out.println("없는 회원이거나 잘못 입력하셨습니다.");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("회원님의 이름을 다시 확인해주세요.");
					}

					break;
				case 3:
					System.out.print("수정할 회원 아이디와 비밀번호를 입력하시오>>");
					sql = "select * from members" + "where userId = ? and userPw = ?";

					try {
						int userId = sc.nextInt();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, userId);
						pstmt.setString(2, sc.next());
						rs = pstmt.executeQuery();
						if (rs.next()) { // 회원이면 true 비회원 false
							sql = "update members set userpw = ?, userName = ?, email = ? , phoneNo = ?"
									+ "where userId = ?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, sc.next());
							pstmt.setString(2, sc.next());
							pstmt.setString(3, sc.next());
							pstmt.setString(4, sc.next());
							pstmt.setInt(5, userId);
							pstmt.executeUpdate();
						} else {
							System.out.println("없는 회원이거나 잘못 입력하셨습니다.");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("회원의 아이디와 비밀번호를 다시 확인해주세요.");
					}
					break;
				case 4:
					System.out.print("삭제할 회원 아이디와 비밀번호를 입력하시오>>");
					sql = "select * from members where userId = ? and userPw = ?";
					try {
						pstmt = conn.prepareStatement(sql);
						int userId = sc.nextInt();
						pstmt.setInt(1, userId);
						pstmt.setString(2, sc.next());
						rs = pstmt.executeQuery();
						if(rs.next()) {
							sql = "delete from members where userId = ? ";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, userId);
							pstmt.executeUpdate();
							System.out.println("삭제 완료하였습니다.");
						}else {
							System.out.println("없는 회원이거나 잘못 입력하셨습니다.");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("아이디와 비밀번호를 다시 확인해주세요.");
					}
					
					break;
				case 5:
					System.out.println("모든 회원의 정보를 확인합니다.");
					sql = "select * from members";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						ProblemMembers m = new ProblemMembers(rs.getInt(1), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6));
						System.out.println(m);						
						
					}
					break;
				case 6:
					rs.close();
					pstmt.close();
					conn.close();
					return;
				default:
					System.out.println("번호를 다시 확인해주세요");

				}
			}

		} catch (Exception e) {
			System.out.println();
		}

	}

}
