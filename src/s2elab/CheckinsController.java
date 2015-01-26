package s2elab;

public class CheckinsController {

	public static String[][] getCheckInsCtrl(int PID)
	{
		Checkin[] c = Checkin.getCheckins(PID);
		String[][] data = new String[c.length][3];
		
		
		for(int i = 0;i < c.length;i++)
		{
			data[i] = c[i].listdata;
		}
		return data;
	}
	
	public static void CheckinCtrl(String place, int PID, String Status)
	{
		//System.out.println(place + " " + PID + " " + Status);
		Checkin.add(place, PID, Status);
	}
}
