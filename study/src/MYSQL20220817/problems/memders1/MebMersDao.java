package MYSQL20220817.problems.memders1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Members {
	int userID;
	String userPwd, userName, email, phoneNo;
	String regDate;

	public Members(int userid, String userpwd, String username, String email, String phoneno, String regdate) {
		this.userID = userid;
		this.email = email;
		this.phoneNo = phoneno;
		this.regDate = regdate;
		this.userPwd = userpwd;
		this.userName = username;
	}

	public Members(String userpwd, String username, String email, String phoneno) {
		this.email = email;
		this.phoneNo = phoneno;
		this.userPwd = userpwd;
		this.userName = username;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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
		return String.format("Members [userid=%d, userName=%s, userPwd=%s, email=%s," + " phoneNo=%s, regDate=%s]",
				getUserID(), getUserName(), getUserPwd(), getEmail(), getPhoneNo(), getRegDate());
	}
}

public class MebMersDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String password = "tiger";
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		int menu = 0;
		int userId = 0;
		String userPwd = null;
		Members mem = null;
		List<Members> al = null;
		conn = getConnectivity(url, user, password);

		BREAK: while (true) {
			System.out.println("========================================");
			System.out.println("1.추가 2.조회 3.수정 4.삭제 5.모두보기 6.종료");
			System.out.println("========================================");
			System.out.println("메뉴를 선택하시오 >> ");
			menu = scan.nextInt();
			switch (menu) {
			case 1: // 추가 insert into members
				System.out.println("비밀번호, 이름, 이메일, 번호 순으로 입력하시오 >> ");
				mem = new Members(scan.next(), scan.next(), scan.next(), scan.next());
				insertMembers(conn, pstmt, mem);
				break;
			case 2: // 조회 select * from members where username = ''
				System.out.println("검색할 회원의 이름을 입력하시오 >> ");
				al = getMembers(conn, pstmt, rs, scan.next());
				showAll(al);
				break;
			case 3: // 수정 아이디 비밀번호가 일치하는 경우(select ..) --> 수정 update members....
				System.out.println("수정할 회원 아이디와 비밀번호를 입력하시오 >> ");
				userId = scan.nextInt();
				userPwd = scan.next();
				if (isMember(conn, pstmt, rs, userId, userPwd)) {
					System.out.println("비밀번호, 이름, 이메일, 번호 순서대로 입력하시오 >> ");
					mem = new Members(scan.next(), scan.next(), scan.next(), scan.next());
					updateMembers(conn, pstmt, mem);
				} else {
					System.out.println("존재하지 않는 회원입니다.");
				}
				break;
			case 4: // 삭제 아이디 비밀번호가 일치하는 경우(select ..) --> 수정 delete from members....
				System.out.println("삭제할 회원 아이디와 비밀번호를 입력하시오 >> ");
				userId = scan.nextInt();
				userPwd = scan.next();
				if (isMember(conn, pstmt, rs, userId, userPwd)) {
					deleteMembers(conn, pstmt, userId);
				} else {
					System.out.println("존재하지 않는 회원입니다.");
				}
				break;
			case 5:
				al = getMemberListAll(conn, pstmt, rs);
				showAll(al);
				break;
			case 6:
				break BREAK;
			}
		}

	}

	public static Connection getConnectivity(String url, String user, String password) {
		// DB와 연결되는 Connection 객체반환
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public static boolean isMember(Connection conn, PreparedStatement pstmt, ResultSet rs, int userId, String userPwd) {
		// 입력된 정보를 가진 회원이 존재여부를 확인하여 회원이면 true, 아니면 false 반환
		String sql = null;
		boolean memberTrue = false;
		sql = "select * from members where userId = ? and userPwd = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if (rs.next())
				memberTrue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberTrue;
	}

	public static void showAll(List<Members> al) {
		// Members객체 리스트의 정보들을 모두 출력
		// String sql = null;
		// sql = "select * from members";
		for (Members mem : al) {
			System.out.println(mem);
		}

	}

	public static List<Members> getMembers(Connection conn, PreparedStatement pstmt, ResultSet rs, String userName) {
		// 이름검색후 해당이름을 가진 Members객체 리스트 반환
		String sql = null;
		List<Members> list = new ArrayList<Members>();
		sql = "select * from members where userName=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Members(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static int insertMembers(Connection conn, PreparedStatement pstmt, Members mem) {
		// 멤버 객체정보를 테이블에 저장
		int insertnum = 0;
		String sql = null;
		sql = "insert into members (userPwd,userName,email,phoneNo) values(?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.userPwd);
			pstmt.setString(2, mem.userName);
			pstmt.setString(3, mem.email);
			pstmt.setString(4, mem.phoneNo);
			insertnum = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return insertnum;

	}

	public static int updateMembers(Connection conn, PreparedStatement pstmt, Members mem) {
		// 회원여부를 판단한 후, 회원인 경우 해당하는 튜플 수정
		int updatenum = 0;
		String sql = null;
		sql = "update members set userPwd=?, userName=?, email=?, phoneNo=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.userPwd);
			pstmt.setString(2, mem.userName);
			pstmt.setString(3, mem.email);
			pstmt.setString(4, mem.phoneNo);
			updatenum = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updatenum;
	}

	public static int deleteMembers(Connection conn, PreparedStatement pstmt, int userId) {
		// 회원인 정보에 해당하는 튜플삭제
		int deletenum = 0;
		String sql = null;
		sql = "delete from members where userId = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			deletenum = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deletenum;
	}

	public static List<Members> getMemberListAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		// 테이블에 저장된 정보들을 리스트 객체에 저장 후 반환
		List<Members> list = new ArrayList<Members>();
		// 테이블에서 꺼내와서(select * from members) new Members() 객체를 생성한 후 리스트에 저장
		String sql = null;
		sql = "select * from members";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Members(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}