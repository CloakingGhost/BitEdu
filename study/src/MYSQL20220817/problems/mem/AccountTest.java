package MYSQL20220817.problems.mem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
	private String accountId;
	private String accountPwd;
	private String userName;
	private int balance;

	Account(String accountId, String accountPwd, String userName, int balance) {
		this.accountId = accountId;
		this.accountPwd = accountPwd;
		this.userName = userName;
		this.balance = balance;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return String.format("Account [accountId=%s, pwd=%s, name=%sm balance=%d]", getAccountId(), getAccountPwd(), getUserName(),
				getBalance());
	}
}

public class AccountTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "scott";
		String pw = "tiger";
		String sql = null;
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = connevtivity(url, user, pw);

		BREAK: while (true) {
System.out.println("--------------------------------------------------------");
System.out.println("|1.계좌개설|2.입금|3.출금|4.송금|5잔액조회|6.전체계좌조회|7.프로그램종료|");
System.out.println("--------------------------------------------------------");
System.out.print("메뉴를 선택하세요>>");
			switch (sc.nextInt()) {
			case 1://계좌개설
				System.out.print("계좌번호,비밀번호,이름 최초 입금액을 입력하세요>>");
				insertAccount(conn,pstmt,new Account(sc.next(),sc.next(),sc.next(),sc.nextInt()));
				System.out.println("등록이 완료되었습니다.");
				break;
			case 2://입금
				System.out.print("입금할 계좌번호와 입금액을 입력하세요>>");
				addBalance(conn,pstmt,sc.next(),sc.nextInt());
				break;
				
			case 3://출금
				break;
			case 4://송금
				break;
			case 5://잔액조회
				break;
			case 6://전체계좌조회
				showAll(conn,pstmt,rs);
				break;

			case 7://종료
				try {
					
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break BREAK;
			}
		}
	}

	public static Connection connevtivity(String url, String user, String pw) {
		//드라이버 호출,연결
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public static int insertAccount(Connection conn,PreparedStatement pstmt,Account a) {
		//계좌계설
		int insertAccount = 0;
		try {
			String sql = "insert into Account values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,a.getAccountId());
			pstmt.setString(2,a.getAccountPwd());
			pstmt.setString(3,a.getUserName());
			pstmt.setInt(4,a.getBalance());
			return insertAccount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insertAccount;
		
	}
	public static boolean isAccount(Connection conn,PreparedStatement pstmt,ResultSet rs,String accountPwd) {
		//계좌확인
		boolean check = false;
		try {
			String sql = "select * from Account where accountPwd = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountPwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
		
	}
	public static int addBalance(Connection conn,PreparedStatement pstmt,String accountId,int add) {
		//입금
		int addBalance = 0;
		try {
			String sql = "update Account set balance = balance + ?  where accountId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,add);
			pstmt.setString(2,accountId);
			return addBalance = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addBalance;
	}
	public static int withdrawBalance(Connection conn,PreparedStatement pstmt,ResultSet rs,String accountId, int wd) {
		//출금
		int withdrawBalance = 0;
		try {
			String sql = "update Account set balance = balance - ?  where accountId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,wd);
			pstmt.setString(2,accountId);
			withdrawBalance = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return withdrawBalance;
	}
	public static List<Account> showAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		//테이블에 저장된 내용을 리스트에 객체로 저장후 각 객체의 toString()실행
		List<Account> a = null;
		try {
			a = new ArrayList<Account>();
			String sql = "select * from Account";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				a.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			for(Account a1 : a) {
				System.out.println(a1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
}
