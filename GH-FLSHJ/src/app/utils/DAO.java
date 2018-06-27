package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	public static String driver = "com.mysql.jdbc.Driver",
					con = "jdbc:mysql://localhost:3306/mydb", req = "";

	Connection conn;
	Statement st;

	public DAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(con, "root", "0000");
			st = conn.createStatement( );
			// res = st.executeQuery(req);
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage( ));
		}
	}

	public ResultSet exec(String query, boolean isupdate) {

		if (!isupdate) {
			try {
				return st.executeQuery(query);
			} catch (SQLException e) {
				return null;
			}
		} else {
			try {
				st.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace( );
			}
			return null;
		}
	}
}
