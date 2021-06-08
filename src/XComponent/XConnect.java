package XComponent;

import java.sql.*;

public class XConnect {

	public static void main(String[] args) {
		Connection con = getConnection();
		System.out.println("Connected: " + con);
	}

	public static Connection getConnection() {
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());
			return DriverManager.getConnection("jdbc:mysql://localhost/carcare", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
