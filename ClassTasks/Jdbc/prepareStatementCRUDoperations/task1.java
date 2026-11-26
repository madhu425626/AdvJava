package com.pack1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JdbcApp4 {
	
	static String driver = "oracle.jdbc.OracleDriver";
	static String	url 	  = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
	static String	uname = "madhu369";
	static String	pwd	  = "madhu123";

	static Connection getCon()
	{
		Connection con = null;
		
		try 
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,uname,pwd);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;		
	}
	
	public void method()
	{
		try
		{
			Connection con = JdbcApp4.getCon();
			PreparedStatement pstmt1 = con.prepareStatement("insert into patient values(?,?,?,?)"); //place holders for values will come by setters
			PreparedStatement pstmt2 = con.prepareStatement("select * from patient");
			PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM PATIENT WHERE ID = (?)");
			PreparedStatement pstmt4 = con.prepareStatement("SELECT * FROM PATIENT WHERE ID =(?)");
			PreparedStatement pstmt5 = con.prepareStatement("DELETE patient WHERE ID =(?)");
			
			//--------updating----
			PreparedStatement UpdateNamePs = con.prepareStatement("UPDATE PATIENT SET NAME = (?) WHERE ID=(?)");
			PreparedStatement UpdateAgePs  = con.prepareStatement("UPDATE PATIENT SET AGE = (?) WHERE ID =(?)");
			PreparedStatement UpdatePhnoPs = con.prepareStatement("UPDATE PATIENT SET PHNO = (?) WHERE ID=(?)");
				
			
			while(true)
			{
				Scanner sc = new Scanner(System.in);
				System.out.println("welcome to patient data.");
				System.out.println("enter any value");
				System.out.println("1) -> add patient");
				System.out.println("2) -> view patient data");
				System.out.println("3) -> retrive patient data");
				System.out.println("4) -> update patient data"); 
				System.out.println("5) -> delete patient date ");
				System.out.println("6) -> exit");
				System.out.println("");
				
				int choice = Integer.parseInt(sc.nextLine());
				switch(choice)
				{
				case 1 ->  //-----------------add------------
				{
					
				System.out.println("enter patient id:");
				 int id = Integer.parseInt(sc.nextLine());
				
				System.out.println("enter patient name");
				String name = (sc.nextLine());

				System.out.println("enter patient age");
				int age = Integer.parseInt(sc.nextLine());

				System.out.println("enter patient phno");
				long phno = Long.parseLong(sc.nextLine());

					pstmt1.setInt(1,id);				//data sent in query but not inserted
					pstmt1.setString(2, name);
					pstmt1.setInt(3,age);
					pstmt1.setLong(4, phno);
					
					int rowCount = pstmt1.executeUpdate();
					if(rowCount == 1)
					{
						
						System.out.println("inserted");
					}
					else 
					{
						System.out.println("record not inserted");
					}

				}	
				
				case 2 ->	 //-----------------view------------
				{
					try
					{
						ResultSet rs = pstmt2.executeQuery();
						while(rs.next())
						{
							System.out.println("id:"+rs.getInt(1)+" "+"name:"+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getLong(4));
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				case 3 ->	 //-----------------retrive------------
				{
					try
					{
						System.out.println("enter patient id:");
						int id = Integer.parseInt(sc.nextLine());
						pstmt3.setInt(1,id);
						int rs = pstmt3.executeUpdate();
						if(rs != 0) 
						{
							ResultSet result = pstmt3.executeQuery();
							System.out.println("id:"+result.getInt(1)+" name:"+result.getString(2)+" Age: "+result.getInt(3)+" phno: "+result.getLong(4));

						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				case 4 ->	 //-----------------update------------
				{
					try
					{
						System.out.println("Enter patient id");
						int id = Integer.parseInt(sc.nextLine());
						pstmt4.setInt(1,id);
						System.out.println("which data you want to update \n"+
											"1)Change Name:\n"+
											"2)Change age:\n"+
											"3)change phno:"+
											"Enter any option");
						
						int option = Integer.parseInt(sc.nextLine());
						
						switch(option)
						{
							case 1 -> //name
							{
								System.out.println("enter newname:");
								String name = sc.nextLine();
								
								UpdateNamePs.setString(1,name);		//--updated values in query
								UpdateNamePs.setInt(2,id);
								
								int resultState = UpdateNamePs.executeUpdate();	//--updated in database
								
								if(resultState != 0)
								{
									
									System.out.println("Name updated");
									
									pstmt4.setInt(1,id);
									ResultSet result = pstmt4.executeQuery();	//--execute qyery
									if(result.next())							//--move cursor
									{
										
			
										System.out.println("id:"+result.getInt(1)+" name:"+result.getString(2)+" Age: "+result.getInt(3)+" phno: "+result.getLong(4));									
									}
								}
								else
								{
									System.out.println("Name not updated");
								}
								
							}
							
							case 2 ->	//age
							{
								System.out.println("enter newage :");
								int age = Integer.parseInt(sc.nextLine());
								
								UpdateAgePs.setInt(1,age);
								UpdateAgePs.setInt(2,id);
								
								
								int result = UpdateAgePs.executeUpdate();
								if(result != 0)
								{
									pstmt3.setInt(1,id);
									System.out.println("Age updated");
									ResultSet rs = pstmt3.executeQuery();
									System.out.println("id:"+rs.getInt(1)+" name:"+rs.getString(2)+" Age: "+rs.getInt(3)+" phno: "+rs.getLong(4));									
								}
								else
								{
									System.out.println("Age not updated");
								}
								
							}
							case 3 ->	//phno
							{
								 pstmt3.setInt(1,id);
								 System.out.println("enter new phno");
								 Long phno = Long.parseLong(sc.nextLine());
								
								 UpdatePhnoPs.setLong(1,phno);
								 UpdatePhnoPs.setInt(2,id);
								 
								 
								 int result = UpdatePhnoPs.executeUpdate();
								 System.out.println(result);
								 if(result != 0)
									{
										System.out.println("Phno updated");
									}
									else
									{
										System.out.println("Phno not updated");
									}
								 
							}
							
							default ->
							{
								System.out.println("invalid choice");
							}
						}
						int result =pstmt4.executeUpdate();
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				case 5 ->	 //-----------------delete------------
				{
					try
					{
						System.out.println("enter patient id");
						int id = Integer.parseInt(sc.nextLine());
						
						pstmt5.setInt(1,id);
						
						int result = pstmt5.executeUpdate();
						if(result == 1)
						{
							System.out.println("row deleted");
						}else {
							System.out.println("not deleted");
						}
						
						//if()
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				case 6 ->	 //-----------------exit------------
				{
					try
					{
						System.out.println("ThankYou😊👍");
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				default -> 
				{
					System.out.println("invalid option select again...");
				}
					
				}
				//Integer.parseInt(sc.nextLine());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
	}
	public static void main(String[] args) {
		JdbcApp4 obj = new JdbcApp4();
		obj.method();
	}
}
