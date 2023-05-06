package com.bridgelabz.jdbc;

import java.sql.*;

/*in JDBC program always import java.sql.* and throws ClassNotFoundException, SQLException on main method
 * and if you are making any other method their also must be thrown SQLException*/

public class DatabaseConnection {

	/* database name- payroll_service */

	public static void retrieveEmployeePayrollData(Connection con) throws SQLException {

		Statement st = con.createStatement();
		String query = "select * from employee_payroll";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {

			/* or int id =rs.getInt(1); */
			int Id = rs.getInt("id");
			String Name = rs.getString("name");
			String Gender = rs.getString(3);
			Double Salary = rs.getDouble(4);
			String joiningDate = rs.getString("start_date");
			int phone = rs.getInt("phone");
			String address = rs.getString("address");
			String depart = rs.getString("department");
			Double basic_pay = rs.getDouble("basic_pay");
			Double deductions = rs.getDouble("deductions");
			Double taxable_pay = rs.getDouble("taxable_pay");
			Double income_pay = rs.getDouble("income_pay");
			
			System.out.println(Id + " " + Name + " " + Gender + " " + Salary + " " + joiningDate+
					" "+phone+" "+address+" "+depart+" "+basic_pay+" "+deductions+" "+taxable_pay+" "+
					income_pay);
		}
	}
	public static void updateEmployeePayrollData(Connection con) throws SQLException {
		Statement st = con.createStatement();
		String query = "update employee_payroll set basic_pay='3000000.00' where name = 'Terissa'";
		int i = st.executeUpdate(query);
		System.out.println(i+"row affected");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "Kalpana@2207";

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection(jdbcURL, username, password);
		System.out.println("Connection establish !");
	
		// --------- UC3 -------
		updateEmployeePayrollData(con);
		System.out.println("employe_payroll table data : ");
		retrieveEmployeePayrollData(con);
	}
}
