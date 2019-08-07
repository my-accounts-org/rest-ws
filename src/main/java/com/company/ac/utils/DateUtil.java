package com.company.ac.utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	
	private static SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat uiDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private static SimpleDateFormat sdf = new SimpleDateFormat();
	
	private DateUtil() {}
	
	public static String format(String date, String pattern) {		
		Calendar now = Calendar.getInstance();
		try {
			now.setTime(dbDateFormat.parse(date));
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
	
	public static Date toDate(String date) throws ParseException {
		return dbDateFormat.parse(date);
	}
	
	public static Date addYear(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.YEAR, amount);
		return cal.getTime();
	}
	
	public static Date addDay(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		String date = "2019-03-31T18:30:00.000Z";
		String formatted = DateUtil.format(date, "yyyy-MMMM-dd");
		System.out.println("Date => "+formatted);
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 04, 16);		
		System.out.println(DateUtil.format(cal.getTime(), "dd MMM, yyyy")); 
		
		String uiDate = "01/24/2019";
		try {
			System.out.println("Date = "+toDate(uiDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
