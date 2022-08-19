package MYSQL20220817.problems.memders1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//auto_increment is in mysql

public class Members2 {
	private int userID;
	private String userPw, userName, email, phoneNo;
	private String regDate;

	Members2(int userID, String userPw, String userName, String email, String phoneNo, String regDate) {
		this.userID = userID;
		this.userPw = userPw;
		this.userName = userName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.regDate = regDate;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String toString() {
		return String.format("Members [userID=%d userName=%s userPw=%s email=%s phoneNo=%s regDate=%s]", userID,
				userName, userPw, email, phoneNo, regDate);
	}

	// DB와 연결되는 Connecton 객체 반환
	public static Connection getConnectivity(String url, String user, String pw) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 입력된 정보를 가진 회원이 존재 여부를 확인하여 회원이면 true, 아니면 false 반환
	public static boolean isMember(Connection conn, PreparedStatement pstmt, ResultSet rs, int userId, String userPw) {
		return false;

	}

	// Memders 객체리스트의 정보들을 모두 출력
	public static void showAll(List<Members2> al) {

	}

	// 이름 검색후 해당 이름을 가진 Members객체 리스트 반환
	public static List<Members2> getMembers(Connection conn, PreparedStatement pstmt, ResultSet rs, String userName) {
		return null;

	}

	// 맴버 객체정보를 테이블에 저장
	public static int insertMember(Connection conn, PreparedStatement pstmt, Members2 mem) {
		return 0;
	}

	// 회원 여부를 판단한 후 , 회원인 경우 해당하는 튜플수정
	public static int updateMemders(Connection conn, PreparedStatement pstmt, Members2 mem) {
		return 0;

	}

	// 회원인 정보에 해당하는 튜플 삭제
	public static int deleteMember(Connection conn, PreparedStatement pstmt, int userId) {
		return 0;
	}

	// 테이블에 저장된 정보들을 리스트 객체에 저장 후 반환
	public static List<Members2> getMenmberListAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		return null;

	}

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String pw = "tiger";
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);

		conn = getConnectivity(url, user, pw);
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
						Members2 m = new Members2(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6));
						System.out.println(m);
					} else {
						System.out.println("없는 회원이거나 잘못 입력하셨습니다.");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("회원님의 이름을 다시 확인해주세요.");
				}

				break;
			case 3:
				System.out.print("수정할 회원 아이디와 비밀번호를 입력하시오>>");
				sql = "select * from members where userId = ? and userPw = ?";

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
					if (rs.next()) {
						sql = "delete from members where userId = ? ";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, userId);
						pstmt.executeUpdate();
						System.out.println("삭제 완료하였습니다.");
					} else {
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
				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Members2 m = new Members2(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6));
						System.out.println(m);

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 6:
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return;
			default:
				System.out.println("번호를 다시 확인해주세요");

			}
		}

	}

}
