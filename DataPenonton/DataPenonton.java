
import java.sql.*;
import javax.swing.*;

public class DataPenonton {

	public static Connection ConnectDB() 
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection
					("jdbc:sqlite:C:\\Dev\\Tutorial Java\\All About Analog\\DataPenonton\\penontonn.db\"  );
					JOptionPane.showMessageDialog(null, "Connection Made");
					return conn;
		}
		catch(Exception e)
		{
			//JOptionPane.showMessageDialog(null, "Connection Er");
			e.printStackTrace();
			return null;	
		}
	
	}
	

}
