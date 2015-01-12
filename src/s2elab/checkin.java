package s2elab;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class Checkin {

	int PID;
	int RID;
	String place;
	String datetime;
	
	Checkin()
	{
		
	}
	
	private Checkin(int _rid, int _pid, String _place, String _datetime)
	{
		PID = _pid;
		RID = _rid;
		place = _place;
		datetime = _datetime;
	
		//System.out.println("PID = " + PID + " RID = " + RID + " place = " + place + " datetime = " + datetime);
	}
	
	public static Checkin[] getCheckins()
	{
		java.sql.Connection conn = null;
		Checkin ch[];
		try {
			
			Class.forName(Data.classpath);
			conn = DriverManager.getConnection(Data.db,Data.uname,Data.passwd);
			ResultSet rsa = conn.createStatement().executeQuery(Data.queryCountCheckin);
			rsa.next();
			
			ch = new Checkin[rsa.getInt("count")];
			
			ResultSet rs = conn.createStatement().executeQuery("Select * from  Checkins");
			
			while(rs.next()){
				//System.out.println(rs.getString("address"));
				Checkin c = new Checkin(rs.getInt("PID"),rs.getInt("RID"),rs.getString("place"),rs.getString("Date"));
				ch[rs.getRow()-1] = c;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("gamithike to syban");
			ch = new Checkin[1];
		}
		return ch;
	}
	
	public static void add(String place, int PID)
	{
		java.sql.Connection conn = null;
		try {
			Class.forName(Data.classpath);
			conn = DriverManager.getConnection(Data.db,Data.uname,Data.passwd);
			String query = "Insert into Checkins values (NULL,'" + place + "'," + PID + ",NULL)";
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("gamithike to syban");
		}
	}
}
