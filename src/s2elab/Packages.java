package s2elab;

import java.sql.DriverManager;

public class Packages {

	int weight;
	String Address;
	String Fragile;
	int ID;
	String Name;
	
	public static void add(int weight, String Address, String Fragile, String Name)
	{
		java.sql.Connection conn = null;
		try {
			Class.forName(Data.classpath);
			conn = DriverManager.getConnection(Data.db,Data.uname,Data.passwd);
			String query = "Insert into  packages VALUES (" + weight + ",'" + Address + "','" + Fragile + "',NULL,'" + Name +"')";
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("gamithike to syban");
		}
	}
}
