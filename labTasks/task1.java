
/**

Assignment01::Write a jdbc program as follows:
===========
Table Name:Employee_Info.
Table Columns:-empId,empName,empSalary,empAddress,empMailId,empPhNo.
Note:-Create table Employee_Info from database directly and perform operations based on user choice.(Use Scanner class to take input from user)
       1.Insert data into Employee Table.
       2.Retrieve all Employee data.
       3.Retrieve employee whose name stats with 'S'.
       4.Retrieve employees whose salary between 10000 to 20000.
       5.Update employee salary with the help of eid.
       6.delete employee who is getting maximum salary.
       7.delete employee whose name ends with 'a';
       8.Exit.
Note:Perform these operations contineously until user press 8(Exit).       


Assignment02:
============
You are tasked with developing a Java application that interacts with a relational database using JDBC. The application needs to perform a series of database operations within a single transaction. Your task is to design and implement a Java program that accomplishes the following:
Connect to a database of your choice (e.g., MySQL, Oracle, PostgreSQL) using JDBC.
 
=>create a table and insert records. your table data should represent as below.

Table Name: Employee:
======================
Column name          data type
-----------	    -----------
Id		     number(8)
name		     varchar2(20)
age		     number(3)
salary		     number(8,2)

Employee Data:
==============
1	Naresh	22	23432.6
2	Suresh	23	32222.5
3	Jain	32	43332.6
4	David	27	15009.2
5	Anthony	34	32322.5

Create Employee class:
======================
instance variables:
-------------------
int id;
String name;
int age;
double salary;

retrive the data from employee table and store it in list<Employee> objecct;

using stream get list of employees who are older than 25 years.

Expected output:
---------------- 
3	Jain	32	43332.6
4	David	27	15009.2
5	Anthony	34	32322.5


*/






package advJavaLabDay1;
import java.sql.DriverManager;	//class
import java.sql.Connection;		//interface
import java.sql.Statement;		//interface
import java.util.ArrayList;
import java.sql.ResultSet;		//interface

record Employee_Info_Table(int eId, String eName)
{
	
}

public class Employee_Info {
	
	String driver = "oracle.jdbc.OracleDriver";
	String DBurl = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
	String uName = "madhu369";
	String uPwd = "madhu123";
	
	public void connect()
	{
		try
		{
			Class.forName(driver); 												//load driver with class.forName()
			Connection con = DriverManager.getConnection(DBurl,uName,uPwd); 		//create connection with getConnection() present in the DriverMangerClass
			//connection created
			Statement smt = con.createStatement();								//create statement with createStatement(
			//statement is created
			ResultSet rs = smt.executeQuery("select * from Employee_Info");							//pass query in executeQuery
			//resultset now having the result of query that means the employee table
			
			while(rs.next())
			{
//				System.out.println("E id:"+rs.getInt(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
//				System.out.println(rs.getString(4));
//				System.out.println(rs.getString(5));
//				System.out.println("");
//				
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				
				//list.add(new Employee_Info(id,name));
				
				
				//System.out.println(rs.getString(""));
			}
			
			System.out.println();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void employee_Info()
	{
		ArrayList<Employee_Info_Table> list = new ArrayList<>();
		//list
		
		try {
			Class.forName("oracle.jdbc.ClassDriver");
			 Connection con = DriverManager.getConnection(DBurl,uName,uPwd);
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("select * from employee_info");
			 
			 while(rs.next()) {
				 int id = rs.getInt(0);
				 String name = rs.getString(1);
				 list.add(new Employee_Info_Table(id,name));
				 
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee_Info e = new Employee_Info();
		e.connect();
	}

}
