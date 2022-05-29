package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program2 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title = "TEST2";
		String writerId = "TEST2";
		String content = "TEST";
		String files = "";
		
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLPDB";
		String sql = "INSERT INTO notice (title,writer_id,content,files) VALUES (?,?,?,?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"STUDY","asdf");
		//Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // �����ϱ� ���� �غ�.
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		// insert, delete�� excuteUpdate�� ���.
		// PreparedStatement�� ����ϸ� SQL���� �Ķ���ͷ� ���������ʴ´�.
		int result = st.executeUpdate();
		System.out.println(result);
		
		st.close();
		con.close();
	}
}