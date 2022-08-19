package MYSQL20220817.problems.mem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Member1 {
	private int userId;
	String userName, userPw, email, phoneNo, regDate;

	Member1(int userId, String userName, String userPw, String email, String phoneNo, String regDate) {
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.email = email;
		this.phoneNo = phoneNo;
		this.regDate = regDate;
	}

	Member1(String userName, String userPw, String email, String phoneNo) {
		this.userName = userName;
		this.userPw = userPw;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	Member1(String userName, String userPw, String email, String phoneNo, int userId) {
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userName=" + userName + ", userPw=" + userPw + ", email=" + email
				+ ", phoneNo=" + phoneNo + ", regDate=" + regDate + "]";
	}
}

public class Memb {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "scott";
		String pw = "tiger";
		Scanner sc = new Scanner(System.in);
		List<Member1> m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// sql java connect
		conn = getConnection(url, user, pw);
		while (true) {
			System.out.println("1.추가 2.조회 3.수정 4.삭제 5.모두보기 6.종료");
			switch (sc.nextInt()) {
			case 1:// 추가
				System.out.println("이름 비밀번호 이메일 전화번호 순으로 입력");
				insertMember(conn, pstmt, new Member1(sc.next(), sc.next(), sc.next(), sc.next()));
				break;
			case 2:// 조회
				System.out.println("검색할 회원의 이름을 입력");
				getMembers(conn, pstmt, rs, sc.next());
				break;
			case 3:// 수정
				System.out.println("회원의 아이디와 비밀번호를 입력");
				int userID = sc.nextInt();
				if (isMember(conn, pstmt, rs, userID, sc.next())) {
					System.out.println("수정할 회원의 이름 비밀번호 이메일 전화번호 순으로 입력");
					updateMember(conn, pstmt, new Member1(sc.next(), sc.next(), sc.next(), sc.next(), userID));
				}
				break;
			case 4:// 삭제
				System.out.println("삭제할 회원의 아이디을 입력");
				deleteMember(conn, pstmt, sc.nextInt());
				break;
			case 5:// 모두보
				getMemberListAll(conn, pstmt, rs);

				break;
			case 6:
				return;
			default:
				System.out.println("다시입력해");
			}
		}
	}

	public static Connection getConnection(String url, String user, String pw) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static boolean isMember(Connection conn, PreparedStatement pstmt, ResultSet rs, int userId, String userPw) {
		String sql = "select * from members where userId = ? and userPw = ?";
		boolean check = false;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return check = true;
			} else {
				System.out.println("다시입력해주세요");
				return check = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public static void showAll(List<Member1> al) {
		for (Member1 m : al) {
			System.out.println(m);
		}
	}

	public static List<Member1> getMembers(Connection conn, PreparedStatement pstmt, ResultSet rs, String userName) {
		List<Member1> m = null;
		try {
			String sql = "select * from members where username = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				m.add(new Member1(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6)));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static int insertMember(Connection conn, PreparedStatement pstmt, Member1 mem) {
		int addMember = 0;
		try {
			String sql = "insert into members (userName,userpw,email,phoneNo) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getUserName());
			pstmt.setString(2, mem.getUserPw());
			pstmt.setString(3, mem.getEmail());
			pstmt.setString(4, mem.getPhoneNo());
			return addMember = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return addMember = 0;
		}

	}

	public static int updateMember(Connection conn, PreparedStatement pstmt, Member1 mem) {
		int updateMember = 0;
		try {

			String sql = "update members set userName=?,userpw=?,email=?,phoneNo=? where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getUserName());
			pstmt.setString(2, mem.getUserPw());
			pstmt.setString(3, mem.getEmail());
			pstmt.setString(4, mem.getPhoneNo());
			pstmt.setInt(5, mem.getUserId());
			return updateMember = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return updateMember = 0;
		}
	}

	public static int deleteMember(Connection conn, PreparedStatement pstmt, int userId) {
		int deleteMember = 0;
		try {
			String sql = "delete from members where userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			return deleteMember = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("없는 회원");
			return deleteMember = 0;
		}

	}

	public static List<Member1> getMemberListAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		List<Member1> m = null;
		try {
			m = new ArrayList<Member1>();
			String sql = "select * from members";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				m.add(new Member1(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6)));
			}
			for (Member1 m1 : m) {
				System.out.println(m1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
}
