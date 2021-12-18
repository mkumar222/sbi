import java.sql.*;

class oracledemo
{
	public static void main(String args[])
	{
		try
		{
			//step1 load driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//step2 create connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","career");
		
			//step3 create SQL Query
			PreparedStatement ps = con.prepareStatement("insert into student_info values(?,?,?,?,?)");
			ps.setInt(1,Integer.parseInt(args[0]));	
			ps.setString(2,args[1]);
			ps.setInt(3,Integer.parseInt(args[2]));
			ps.setString(4,args[3]);
			ps.setString(5,args[4]);
			
			//step4 execute SQL Query
			int z = ps.executeUpdate();
			if(z>0)
			{
				System.out.println("record inserted successfully......");
			}
			else
			{
				System.out.println("record not inserted......");
			}

			//step5 close connection
			con.close();
			
		}
		catch(Exception e)
		{
		}
	}
}