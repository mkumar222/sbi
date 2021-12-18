import java.text.*;
import java.sql.*;

class date1
{
	public static void main(String[] args)
	{
		try
		{
			String strdate = "15/Aug/1900";
 
     			java.util.Date javadate = new SimpleDateFormat("dd/MMM/yyyy").parse(strdate);  
   			System.out.println("JavaDate : " + javadate); 

			java.sql.Date sqldate = new java.sql.Date(javadate.getTime()); 
			System.out.println("SQLDate : " + sqldate); 

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","career");
			PreparedStatement ps = con.prepareStatement("insert into oracle_date values(?,?)");
			ps.setString(1,"tanya");		
			ps.setDate(2,sqldate);
			int z = ps.executeUpdate();
			if(z>0)
			{
				System.out.println("record inserted successfully....");
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);	
		} 
	}
}