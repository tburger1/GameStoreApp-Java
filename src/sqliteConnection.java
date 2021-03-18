import java.sql.*;
import javax.swing.*;

public class sqliteConnection 
{
	Connection conn = null;
	
	public static Connection dbConnector() 
	{
		try 
		{
			String url = "jdbc:sqlite:G:\\School\\5 - Winter 2020\\CSCI239 - Object-Oriented Program Java\\Code Repository\\GameStore\\bin\\Game Store.db";
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(url);
			return conn;
		}
		
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
