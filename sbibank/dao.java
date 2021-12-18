import java.sql.*;

class dao
{
	public static Connection createconnection()
	{
		Connection con=null;
		try
		{
			//step1 load driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
			//step2 create connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","12345");
		}
		catch(Exception e)
		{
		}
		return con;
	} 
}