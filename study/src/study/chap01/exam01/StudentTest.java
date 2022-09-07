//package study.chap01.exam01;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//class Student {
//	private String name;
//	private int kor;
//	private int eng;
//	private int math;
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getKor() {
//		return kor;
//	}
//
//	public void setKor(int kor) {
//		this.kor = kor;
//	}
//
//	public int getEng() {
//		return eng;
//	}
//
//	public void setEng(int eng) {
//		this.eng = eng;
//	}
//
//	public int getMath() {
//		return math;
//	}
//
//	public void setMath(int math) {
//		this.math = math;
//	}
//
//	Student(String name, int kor, int eng, int math) {
//		this.name = name;
//		this.kor = kor;
//		this.eng = eng;
//		this.math = math;
//	}
//
//	int total() {
//		return kor + eng + math;
//	}
//
//	int avg() {
//		return total() / 3;
//	}
//}
//
//public class StudentTest {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "";
//		Scanner sc = new Scanner(System.in);
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scottdb?useSSL=false", "scott", "tiger");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		while (true) {
//			System.out.println("1.성적추가2.성적목록3.파일로저장4.최고점자조회5.종료");
//
//			switch (sc.nextInt()) {
//			case 1:
//				System.out.println("이름 국어 영어 수학 순서대로 입력");
//				Student s = new Student(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());
//				sql = "insert into students values(?,?,?,?,?,?)";
//				try {
//					pstmt = conn.prepareStatement(sql);
//					pstmt.setString(1, s.getName());
//					pstmt.setInt(2, s.getKor());
//					pstmt.setInt(3, s.getEng());
//					pstmt.setInt(4, s.getMath());
//					pstmt.setInt(5, s.total());
//					pstmt.setInt(6, s.avg());
//					pstmt.executeUpdate();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			case 2:
//				sql = "select * from students";
//				try {
//					pstmt = conn.prepareStatement(sql);
//					rs = pstmt.executeQuery();
//					while (rs.next()) {
//						System.out.printf("%s %d %d %d %d %d\n", rs.getString(1), rs.getInt(2), rs.getInt(3),
//								rs.getInt(4), rs.getInt(5), rs.getInt(6));
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				break;
//			case 3:
//				String str ="";
//				sql = "select * from students";
//				
//				try {
//					pstmt = conn.prepareStatement(sql);
//					rs = pstmt.executeQuery();
//					while (rs.next()) {
//						
//						str = rs.getString(1)+"," +rs.getInt(2)+"," + rs.getInt(3)+"," +
//								rs.getInt(4)+"," + rs.getInt(5)+"," + rs.getInt(6);
//						System.out.println(str);
//						BufferedReader br = new BufferedReader(new InputStreamReader(str));
//						OutputStream os = new FileOutputStream("E:\\temp\\asdf.txt");
//						while(is.available()>0) {
//							int b =is.read();
//							os.write(b);
//						}
//						
//					}
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				break;
//			case 4:
//				sql = "select * from students where sum = (select max(sum) from students)";
//
//				try {
//					pstmt = conn.prepareStatement(sql);
//					rs = pstmt.executeQuery();
//					while (rs.next()) {
//						System.out.printf("%s %d %d %d %d %d\n", rs.getString(1), rs.getInt(2), rs.getInt(3),
//								rs.getInt(4), rs.getInt(5), rs.getInt(6));
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			case 5:
//				return;
//			default:
//				System.out.println("메뉴번호를 확인해주세요");
//			}
//		}
//	}
//
//}
//
//// conan 89 34 24
