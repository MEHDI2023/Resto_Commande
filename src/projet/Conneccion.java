package projet;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conneccion {
	Connection cn;
	public Conneccion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/resto_db","root","");
	    System.out.println("Connection �tablie!");
		}
		catch(Exception e){
			System.out.println("non connect�e!");
			
		}
		
	}
    public Connection laConnection(){
    	return cn;
    }
}