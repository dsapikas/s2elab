package s2elab;

public class Data {

	static String db = "jdbc:mysql://localhost/dadme";
	static String uname = "root";
	static String passwd = "";
	static String classpath = "com.mysql.jdbc.Driver";
	static String queryCountCheckin = "SELECT COUNT(*) as count FROM checkins";
	static String queryCountPackages = "SELECT COUNT(*) as count FROM packages";
}
