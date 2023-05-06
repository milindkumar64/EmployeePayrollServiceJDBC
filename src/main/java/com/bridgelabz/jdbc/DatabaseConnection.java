package com.bridgelabz.jdbc;

import java.sql.*;

/*in JDBC program always import java.sql.* and throws ClassNotFoundException, SQLException on main method
 * and if you are making any other method their also must be thrown SQLException*/

public class DatabaseConnection {

	/* database name- payroll_service */
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "Kalpana@2207";

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection(jdbcURL, username, password);
		System.out.println("Connection establish !");
	}
}
