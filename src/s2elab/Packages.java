package s2elab;

import java.sql.DriverManager;
import java.sql.ResultSet;

public class Packages {

	int weight;
	String Address;
	String Fragile;
	int ID;
	String Name;
	
	public Packages (int _weight, String _Address, String _Fragile, String _Name, int _ID )
	{
		weight = _weight;
		Address = _Address;
		Fragile = _Fragile;
		Name = _Name;
		ID = _ID;
	}
	
	public static Packages[] getPackages()
	{
		java.sql.Connection conn = null;
		Packages pa[];
		try {
			
			Class.forName(Data.classpath);
			conn = DriverManager.getConnection(Data.db,Data.uname,Data.passwd);
			ResultSet rsa = conn.createStatement().executeQuery(Data.queryCountPackages);
			rsa.next();
			
			pa = new Packages[rsa.getInt("count")];
			
			ResultSet rs = conn.createStatement().executeQuery("Select * from  packages");
			
			while(rs.next()){
				//System.out.println(rs.getString("address"));
				Packages p = new Packages(rs.getInt("weight"),rs.getString("address"),rs.getString("fragile"),rs.getString("name"),rs.getInt("ID"));
				pa[rs.getRow()-1] = p;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Something went wrong");
			pa = new Packages[1];
		}
		return pa;
	}
	
	public static int add(int weight, String Address, String Fragile, String Name)
	{
		java.sql.Connection conn = null;
		try {
			Class.forName(Data.classpath);
			conn = DriverManager.getConnection(Data.db,Data.uname,Data.passwd);
			String query = "Insert into  packages VALUES (" + weight + ",'" + Address + "','" + Fragile + "',NULL,'" + Name +"')";
			
			conn.createStatement().executeUpdate(query);
			query = "Select ID from packages where weight = '" + weight + "' AND address = '" + Address + "' AND name = '" + Name + "' AND fragile = '" + Fragile + "'";
			//System.out.println(query);
			ResultSet rsa = conn.createStatement().executeQuery(query);
			rsa.next();
			
			return rsa.getInt("ID");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Something went wrong");
			return -1;
		}
		
	}
	
	public static String Weight(String par)
	{
		java.sql.Connection conn = null;
		try {
			
			Class.forName(Data.classpath);
			conn = DriverManager.getConnection(Data.db,Data.uname,Data.passwd);
			//theoritika i parametro "par" tha eprepe na ginete sanitized i na elegxete gia tyxon apopeira SQL INJECTION, alla sti sygkekrimeni periptosi einai peritto.
			ResultSet rsa = conn.createStatement().executeQuery("Select " + par + "(weight) as result from packages");
			rsa.next();
			
			return rsa.getString("result");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("gamithike to syban");
			return "0";
		}
	}
}
