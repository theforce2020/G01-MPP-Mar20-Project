package util;

import java.util.Calendar;
import java.util.Date;

public class LibraryUtil {
	
	public static Date calculateDueDate(Date oldDate, int numberOfDays) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(oldDate);
		calender.add(Calendar.DAY_OF_MONTH, numberOfDays);  
		return calender.getTime();  
	}
}