package s2elab;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;

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
			System.err.println("Something went wrong");
			return "0";
		}
	}
	
	public static int[] getPostCodes()
	{
		java.sql.Connection conn = null;
		int pa[];
		try {
			
			Class.forName(Data.classpath);
			conn = DriverManager.getConnection(Data.db,Data.uname,Data.passwd);
			ResultSet rsa = conn.createStatement().executeQuery("Select count(*) as count from packages");
			rsa.next();
			
			pa = new int[rsa.getInt("count")];
			
			ResultSet rs = conn.createStatement().executeQuery("Select address from  packages");
			
			while(rs.next()){
				//System.out.println(rs.getString("address"));
				pa[rs.getRow()-1] = Integer.parseInt(rs.getString("address").split(", ")[1]);
				//pa[rs.getRow()-1] = int.class.cast(rs.getString("address").split(",")[1].split(" ")[1]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Something went wrong");
			pa = new int[0];
		}
		return pa;
	}
	
	public static HashMap getHash()
	{
		int[] data = getPostCodes();
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i = 0; i< data.length; i++)
		{
			Integer val = map.get(data[i]);
			//val != null ? map.put(data[i], val + 1) : map.put(data[i],1);
			if (val != null)
				map.put(data[i], val + 1);
			else
				map.put(data[i],1);
		}
		return map;
		
	}
	
	public static String[][] strArrMap(HashMap mp) {
		String[][] re = new String[mp.size()][2];
		int i = 0;
	    Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry pairs = (HashMap.Entry)it.next();
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        re[i][0] = pairs.getKey().toString();
	        re[i][1] = pairs.getValue().toString();
	        i++;
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    return re;
	}
}
