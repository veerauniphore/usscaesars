package util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;

public class DBConncetivity {
	
	
	public Connection DBConnection(String DB_url, String DB_USERNAME, String DB_PASSWORD, String DB_DATABASE ) {
		System.out.println("DB_url"+DB_url+DB_USERNAME+DB_PASSWORD+DB_DATABASE);
	      Connection connection = null;
	      String driverName = "org.postgresql.Driver";
	      String url = "jdbc:postgresql://"+DB_url+":5432/"+DB_DATABASE+"";
	      String username = DB_USERNAME;
	      String password = DB_PASSWORD;
	      
//	      local
//	      String url = "jdbc:postgresql://localhost:5432/report";
//	      String username = "postgres";
//	      String password = "postgres";

	      
	      System.out.println("url"+url);
	         try {
				Class.forName(driverName);
					connection = DriverManager.getConnection(url, username, password);
					if(null != connection) {
		        	}else{
		        		System.out.println("failed to connect db");
			    	  }	
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}

	      return connection;
	   }
}
