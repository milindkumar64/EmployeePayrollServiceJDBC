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

			System.out.println(
					Id + " " + Name + " " + Gender + " " + Salary + " " + joiningDate + " " + phone + " " + address
							+ " " + depart + " " + basic_pay + " " + deductions + " " + taxable_pay + " " + income_pay);
		}
	}

	public static void updateEmployeePayrollData(Connection con) throws SQLException {
		Statement st = con.createStatement();
		String query = "update employee_payroll set basic_pay='3000000.00' where name = 'Terissa'";
		int i = st.executeUpdate(query);
		System.out.println(i + "row affected");
	}

	public static void updateEmployeePayrollDataUsingPreparedStatement(Connection con) throws SQLException {

		String query = "update employee_payroll set basic_pay='4000000.00' where name = 'Terissa'";
		PreparedStatement ps = con.prepareStatement(query);
		ps.executeUpdate();
	}

	public static void retrieveEmployeeWhoJoinedInParticularDateRange(Connection con) throws SQLException {
		String query = "select * from employee_payroll where start_date between cast('2017-06-28' as date) and date(now())";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String name = rs.getString("name");
			System.out.println(name + " ");
		}
	}
	public static void findSumMiscellaneousResults(Connection con) throws SQLException {
	
		String query1 = "Select min(salary) from employee_payroll where gender = 'F' group by gender";
		String query2 = "Select max(salary) from employee_payroll where gender = 'F' group by gender";
		String query3 = "Select count(name) from employee_payroll";
		String query4 = "select avg(salary) from employee_payroll";
		String query5 = "Select sum(salary) from employee_payroll where gender ='F' group by gender";
		
		PreparedStatement ps1 = con.prepareStatement(query1);
		PreparedStatement ps2 = con.prepareStatement(query2);
		PreparedStatement ps3 = con.prepareStatement(query3);
		PreparedStatement ps4 = con.prepareStatement(query4);
		PreparedStatement ps5 = con.prepareStatement(query5);
		
		ResultSet rs1 = ps1.executeQuery();
		ResultSet rs2 = ps2.executeQuery();
		ResultSet rs3 = ps3.executeQuery();
		ResultSet rs4 = ps4.executeQuery();
		ResultSet rs5 = ps5.executeQuery();
		while(rs1.next())
		{
			System.out.println("min(salary): "+rs1.getString(1));
		}	
		while(rs2.next())
		{
			System.out.println("max(salary): "+rs2.getString(1));
		}	
		while(rs3.next())
		{
			System.out.println("count(name)"+rs3.getString(1));
		}	
		while(rs4.next())
		{
			System.out.println("avg(name)"+rs4.getString(1));
		}	
		while(rs5.next())
		{
			System.out.println("sum(name)"+rs5.getString(1));
		}	
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
		
		// --------- UC4 -------
		updateEmployeePayrollDataUsingPreparedStatement(con);
		
		//--------- UC5 -------
		System.out.println("Employees Who joined after date '2017-06-28' :");
		retrieveEmployeeWhoJoinedInParticularDateRange(con);
		System.out.println("employe_payroll table data : ");
		retrieveEmployeePayrollData(con);
		
		
		//--------- UC6 -------
		findSumMiscellaneousResults(con);
		
		
	}

}
