package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program3 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title = "TEST3";
		String content = "TEST";
		String files = "";
		int id = 4;
		
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLPDB";
		// 잘봐야한다. NOTICE SET이므로 띄어쓰기가 필요하다
		String sql = "UPDATE NOTICE "
				+ " SET TITLE=?, CONTENT=?,FILES=?"
				+ "WHERE ID=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"STUDY","asdf");
		//Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // 실행하기 전에 준비.
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		// insert, delete는 excuteUpdate를 사용.
		// PreparedStatement를 사용하면 SQL문을 파라미터로 보내주지않는다.
		int result = st.executeUpdate();
		System.out.println(result);
		
		st.close();
		con.close();
	}
}