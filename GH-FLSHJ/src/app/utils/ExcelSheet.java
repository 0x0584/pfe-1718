package app.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import app.EmployeeType;
import wins.MainWin;

public class ExcelSheet {
	private static final String DRIVER_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String DATABASE_URL = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=%s;DriverID=22;READONLY=false";

	private static final String FILEPATH = "C:/Documents and Settings/web/Desktop/Employee.xlsx";

	private static Connection con = null;
	private static Statement stmt = null;

	@SuppressWarnings("unused")
	private Connection getConnection(File file) {
		try {
			Class.forName(DRIVER_NAME);
			Connection con = DriverManager.getConnection(
				String.format(DATABASE_URL, file.getAbsolutePath( )));
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage( ));
		}

		return con;
	}

	public void writeData(JTable table, File file) {
		try {
			TableModel model = table.getModel( );
			FileWriter excel = new FileWriter(file);

			for (int i = 0; i < model.getColumnCount( ); i++) {
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for (int i = 0; i < model.getRowCount( ); i++) {
				for (int j = 0; j < model.getColumnCount( ); j++) {
					excel.write(model.getValueAt(i, j).toString( ) + "\t");
				}
				excel.write("\n");
			}

			excel.close( );

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public List<String> getData(File file,
		String[] columns) throws SQLException {

		List<String> list = new ArrayList<String>( );

		try {
			ResultSet rs = getRecord(file, "select emailid from [Sheet1$]");
			ResultSetMetaData rsmd = rs.getMetaData( );
			System.out.println(rsmd.getColumnCount( ));

			while (rs.next( )) {
				for (int i = 0; i < columns.length; i++) {
					list.add(rs.getString(columns[i]));
				}
			}
			rs.close( );
		} catch (Exception e) {}

		closeConnection( );
		return list;

	}

	private void closeConnection( ) {
		try {
			if (stmt != null) {
				stmt.close( );
				stmt = null;
			}

			if (con != null) {
				con.close( );
				con = null;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}
	}

	ResultSet getRecord(File file, String query) {
		con = getConnection(file);
		ResultSet rs = null;

		try {
			stmt = con.createStatement( );
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		return rs;
	}

	public static void main(String[] args) {
		/*
		 * ExcelSheetReader reader = new ExcelSheetReader(); String[] columns =
		 * {"emailid"}; File file = new File(FILEPATH); List<String> list =
		 * null; try { list = reader.getData(file,columns); } catch
		 * (SQLException e) { e.printStackTrace(); } for (Iterator<String>
		 * iterator = list.iterator(); iterator.hasNext();) { String string =
		 * (String) iterator.next(); System.out.println(string); }
		 */
		ExcelSheet reader = new ExcelSheet( );
		File file = new File(FILEPATH);
		if (file.exists( )) {
			try {
				ResultSet rs = reader.getRecord(
					file, "select emailid from [Sheet1$]");
				ResultSetMetaData rsmd = rs.getMetaData( );
				int count = rsmd.getColumnCount( );
				// System.out.println(count);
				while (rs.next( )) {
					for (int i = 1; i <= count; i++) {
						System.out.println(
							rsmd.getColumnName(i) + ": " + rs
											.getString(rsmd.getColumnName(i)));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace( );
			}
		}

		reader.writeData(
			new JTable(MainWin.getDefaultModel(EmployeeType.All)),
			new File("foo.xlsx"));
	}
}