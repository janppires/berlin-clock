package org.ink.europeanclocks.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.ink.europeanclocks.models.clocks.ClockSource;

/**
 * <code>GregorianCalendarClockSource</code> is an implementation of
 * <code>ClockSource</code> to provide a source from a gregorian calendar.
 * 
 * */
public class GregorianCalendarClockSource implements ClockSource{

	private final String pattern;

	private static final String defaultPattern = "HH:mm:ss";
	
	private GregorianCalendar gregorianCalendar;
	
	/**
	 * Constructs a <code>GregorianCalendarClockSource</code> using the default pattern 'HH:mm:ss'.
	 * */
	public GregorianCalendarClockSource(){
		this(defaultPattern);
	}
	
	/**
	 * Constructs a <code>GregorianCalendarClockSource</code> using the given pattern. 
	 * @param pattern the pattern describing the date and time format
	 * */
	public GregorianCalendarClockSource(String pattern){
		
		if(pattern == null || pattern.isEmpty()){
			throw new IllegalArgumentException("Invalid pattern!");
		}
		
		this.pattern = pattern;
		this.gregorianCalendar = getNewGregorianCalendarInstance();
	}
	
	public int getHour() {
		return gregorianCalendar.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		return gregorianCalendar.get(Calendar.MINUTE);
	}


	public int getSecond() {
		return gregorianCalendar.get(Calendar.SECOND);
	}

	public String getHumanReadableFormat() {
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(gregorianCalendar.getTime());
	}

	public void generate() {
		this.gregorianCalendar = getNewGregorianCalendarInstance();
	}
	
	private static GregorianCalendar getNewGregorianCalendarInstance(){
		return new GregorianCalendar();
	}

	protected void setCalendar(GregorianCalendar calendar) {
		this.gregorianCalendar = calendar;
	}
}
