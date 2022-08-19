package MYSQL20220817.exam01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class BlobExample {

	public static void main(String[] args) {
		String  url = "jdbc:mysql://localhost:3306/scottdb?useSSL=false";
		String user = "scott";
		String pw = "tiger";
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 자바와 드라이버 연결(연결객체 생성)
			conn = DriverManager.getConnection(url, user, pw);
			//준비
			sql = "INSERT INTO IMAGES VALUES(?,?)"; 
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "cash");
		FileInputStream fin = new FileInputStream("E:/temp/cash.png");
		pstmt.setBinaryStream(2,fin ,fin.available());
//		int num = pstmt.executeUpdate();// 나두면 계속 삽입됨
//		System.out.println(num + "개의 레코드 삽입");
		//실행
		pstmt = conn.prepareStatement("SELECT * FROM IMAGES");
		rs =pstmt.executeQuery();
		if(rs.next()) {
			Blob b= rs.getBlob(2);
			byte barr[]=b.getBytes(1,(int)b.length());
			FileOutputStream fout = new FileOutputStream("E:\\temp/cash1.png");
			fout.write(barr);
			fout.close();
		}
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}

	}

}
