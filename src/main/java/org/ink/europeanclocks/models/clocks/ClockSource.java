package org.ink.europeanclocks.models.clocks;

public interface ClockSource {

	/**
	 * Returns the value for the Hour of Day
	 * @return the value for the Hour of Day.
	 * */
	int getHour();
	
	/**
	 * Returns the value for the minute
	 * @return the value for the minute.
	 * */
	int getMinute();
	
	/**
	 * Returns the value for the second
	 * @return the value for the second.
	 * */
	int getSecond();
	
	/**
	 * Returns a human readable string of the time format, according to the selected pattern
	 * @return a human readable string of the time format, according to the selected pattern.
	 * */
	String getHumanReadableFormat();
	
	/**
	 * Generate a new instance of time, if necessary. The call of this method is optional.
	 * */
	void generate();
}
