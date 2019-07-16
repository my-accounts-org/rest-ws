package com.company.ac.utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	
	private static SimpleDateFormat uiDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf = new SimpleDateFormat();
	
	private DateUtil() {}
	
	public static String format(String date, String pattern) {		
		Calendar now = Calendar.getInstance();
		try {
			now.setTime(uiDateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sdf.applyPattern(pattern);
		return sdf.format(now.getTime());
	}
	
	public static String format(Date date, String pattern) {
		sdf.applyPattern(pattern);
		return sdf.format(date);
		
	}	
	
	public static void main(String[] args) {
		String date = "2019-03-31T18:30:00.000Z";
		String formatted = DateUtil.format(date, "yyyy-MMMM-dd");
		System.out.println("Date => "+formatted);
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 04, 16);		
		System.out.println(DateUtil.format(cal.getTime(), "dd MMM, yyyy")); 
	}
}
