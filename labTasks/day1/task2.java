/**
*

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Employee_manage {

	
	static String driver = "oracle.jdbc.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
	static String	id = "madhu369";
	static String	pass  = "madhu123";
	
	static List<Employee> listOfEmp = new ArrayList<>();
	static Connection con ()
	{
		Connection con = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pass);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
			
		Connection con = Employee_manage.con();
		Statement smt = con.createStatement();
		String query = "SELECT * FROM employee_info";
		ResultSet rs = smt.executeQuery(query);
		
		while(rs.next())
		{
			int id 		= rs.getInt(1);
			String name = rs.getString(2);
			int sal		= rs.getInt(3);
			String add	= rs.getString(4);
			String email	= rs.getString(5);
			long phno	= rs.getLong(6);
			int age		= rs.getInt(7);
			
			listOfEmp.add(new Employee(id,name,sal,add,email,phno,age));
			
		}
			System.out.println("===============List of the employees=============== ");
			for(Employee e: listOfEmp)
			{							
				System.out.println(e);				
			}
			
			empAbove25(listOfEmp);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	
	public static void empAbove25(List<Employee> listOfEmpAbove25)
	{
		System.out.println("-------------------Employees having age more than 25------------------");
		
		for(Employee e: listOfEmpAbove25)
		{			
			if(e.age>25)
			{
				System.out.println(e.ename);
			}
		}
	}

}

class Employee
{
	Integer eid;
	String ename;
	double esal;
	String eadd;
	String email;
	Long phno;
	int age;
	
	public Employee(Integer eid, String ename, double esal, String eadd, String email, Long phno,int age) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.esal = esal;
		this.eadd = eadd;
		this.email = email;
		this.phno = phno;
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eadd, eid, email, ename, Double.valueOf(esal), phno);
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(eadd, other.eadd) && Objects.equals(eid, other.eid) && Objects.equals(email, other.email)
				&& Objects.equals(ename, other.ename)
				&& Double.doubleToLongBits(esal) == Double.doubleToLongBits(other.esal)
				&& Objects.equals(phno, other.phno);
	}

	@Override
	public String toString() {
		return "Employee_info [eid=" + eid + ", ename=" + ename + ", esal=" + esal + ", eadd=" + eadd + ", email="
				+ email + ", phno=" + phno +", Age="+age+"]";
	}
	
	
}













  
