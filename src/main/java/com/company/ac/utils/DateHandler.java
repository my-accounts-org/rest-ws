package com.company.ac.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateHandler {
	
	private String DB_LONG_DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}T.*";
	private String UI_DATE_PATTERN = "\\d{2}/\\d{2}/\\d{4}";
	
	private LocalDate localDate;
	
	private static DateHandler INSTANCE = new DateHandler();
	
	private static DateTimeFormatter uiDate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
	private static DateTimeFormatter dbDate = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	private static DateTimeFormatter toUIFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);

	public static DateHandler getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @param date - in one of three formats 1) MM/dd/yyyy 2) yyyy-MM-dd 3)yyyy-MM-dd'T'HH:mm:ss.SSS'Z' 
	 * @return date in db format (yyyy-MM-dd)
	 */
	public String format(String date) {			
		DateTimeFormatter input = getFormatter(date);
		localDate = LocalDate.parse(date, input);		
		return dbDate.format(localDate);
	}
	
	public String format(java.util.Date date) {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	private DateTimeFormatter getFormatter(String date) {
		DateTimeFormatter formatter = dbDate;
		if(date != null && !date.isEmpty()) {
			if (date.matches(DB_LONG_DATE_PATTERN))  {				
				formatter = uiDate;
			} else if(date.matches(UI_DATE_PATTERN)) {
				formatter = toUIFormat;
			} 
		}		
		return formatter;
	}
	
	public Map<String, String> getFinancialYearDates(String date) {
		String from = format(date);		
		localDate = localDate.plusYears(1).withMonth(3).withDayOfMonth(31);		
		
		String to = dbDate.format(localDate);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("from", from);
		map.put("to", to);
		map.put("start", from);
		map.put("end", to);
		
		return map;
	}
	
	public String formatToUI(String date) {
		format(date);			
		return toUIFormat.format(localDate);
	}
		

}
