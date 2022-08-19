package MYSQL20220817.problems.memders1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProblemMembers {
	private int userID;
	private String userPw, userName,
					email, phoneNo;
	private	String regDate;//가입일 시스템 시간이 자동 기입 now()or date()
	ProblemMembers(int userID, String userName, String userPw, String email, String phoneNo, String regDate) {
		this.userID = userID;
		this.userName = userName;
		this.userPw = userPw;
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
		return String.format("Members [userID=%d userName=%s userPw=%s email=%s phoneNo=%s regDate=%s]", userID, userName, userPw, email, phoneNo, regDate);
	}
}
