package mmms;

import java.sql.Connection;
import java.sql.DriverManager;




public class Dbsconnect  {

	public Connection getconnection(){
   	 Connection connection;
   	 try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/m_m_m_s?autoReconnect=true&useSSL=false", "root", "root");
			return connection;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
    }
	
	

}
