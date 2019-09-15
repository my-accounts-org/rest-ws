package com.company.ac.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DateUtil {
	
	private static DateTimeFormatter inUIFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
	private static DateTimeFormatter toDBFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	private static DateTimeFormatter toUIFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
		
	private DateUtil() {}
	
	public static String toDBDate(String date) {				
		LocalDate localeDate = LocalDate.parse(date, toUIFormat);
		String formattedDate = toDBFormat.format(localeDate);
		return formattedDate;
	}
	
	public static String toUIDate(String date) {
		LocalDate localeDate = LocalDate.parse(date, toDBFormat);
		String formattedDate = toUIFormat.format(localeDate);
		return formattedDate;
	}
	
	public static String toDBDate(Date date) {
		
		return null;
	}
	
	public static Map<String, String> getFinancialYearDates(String financialYear) {
		LocalDate localeDate = LocalDate.parse(financialYear, toDBFormat);
		localeDate = localeDate.plusYears(1).withMonth(3).withDayOfMonth(31);
		
		String from = (financialYear);
		String to = toDBFormat.format(localeDate);
		Map<String, String> map = new HashMap<String, String>();
		map.put("from", from);
		map.put("to", to);
		map.put("start", from);
		map.put("end", to);
		
		return map;
	}

	
	
	
	public static Date addYear(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, amount);
		return cal.getTime();
	}
	
	
	public static Date getFinancialEndDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, 1);
		
		cal.set(Calendar.DATE, 31);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		String date = "2019-03-31T18:30:00.000Z";
		date ="Thu Jun 13 2019 00:00:00 GMT+0530";
		date="2019-08-31T18:30:00.000Z";
		String formatted = DateUtil.toDBDate(date);
		//System.out.println("Date => "+formatted);
		
		//date = "2019-08-20";
		//formatted = DateUtil.toUIDate(date);
		System.out.println("Date (1) => "+DateUtil.getFinancialYearDates(date));
		
		
		
	}

	


}
