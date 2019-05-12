package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemDateAndTime 
{
	
	public static String getSystemDateTime()
	{
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ssa dd-MMM-yyyy");
	String date1 = sdf.format(date);
	//System.out.println(date1);
	return date1;
	}
	
}
