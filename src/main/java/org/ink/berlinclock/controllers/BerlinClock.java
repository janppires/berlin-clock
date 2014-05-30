package org.ink.berlinclock.controllers;

import java.util.List;

import org.ink.berlinclock.models.clocks.Clock;
import org.ink.berlinclock.models.clocks.ClockPosition;
import org.ink.berlinclock.models.clocks.ClockSource;
import org.ink.berlinclock.models.lamprow.LampRow;
import org.ink.berlinclock.models.lamprow.LampRow.LampColor;
import org.ink.berlinclock.models.printer.Printer;

/**
 * <code>BerlinClock</code> is an implementation of
 * <code>Clock</code> and provides the behavior of a Berlin Clock.
 * 
 * This class uses the strategy pattern for <code>ClockSource</code> and <code>Printer</code>
 * */
public class BerlinClock implements Clock {

	private final ClockPosition clockPosition;
	private final ClockSource clockSource;
	private final Printer printer;
	
	private final int HOUR_INTERVAL = 5;
	private final int MINUTE_INTERVAL = 5;
	
	private LampRow secondLampRow;
	private LampRow fiveHourLampRow;
	private LampRow oneHourLampRow;
	private LampRow fiveMinuteLampRow;
	private LampRow oneMinuteLampRow;
	
	/**
	 * Constructs a <code>BerlinClock</code>.
	 * @param clockPosition represents the position of the clock according to <code>ClockPosition</code>
	 * @param clockSource is the source of the clock
	 * @param printer allow the clock to be printed
	 * */
	public BerlinClock(ClockPosition clockPosition, ClockSource clockSource, Printer printer){
		
		if(clockPosition == null){
			throw new IllegalArgumentException("clockPosition is null.");
		}
	
		if(clockSource == null){
			throw new IllegalArgumentException("clockSource is null.");
		}
		
		if(printer == null){
			throw new IllegalArgumentException("printer is null.");
		}
		
		this.clockPosition = clockPosition;
		this.clockSource = clockSource;
		this.printer = printer;
		
		defaultSetup();
	}
	
	/**
	 * Provide a berlin clock default setup for the lamp rows
	 * */
	private void defaultSetup(){
		secondLampRow = LampRowFactory.getRow(LampRowFactory.RowStyle.TWO_SECOND);
		fiveHourLampRow = LampRowFactory.getRow(LampRowFactory.RowStyle.FIVE_HOUR);
		oneHourLampRow = LampRowFactory.getRow(LampRowFactory.RowStyle.ONE_HOUR);
		fiveMinuteLampRow = LampRowFactory.getRow(LampRowFactory.RowStyle.FIVE_MINUTE);
		oneMinuteLampRow = LampRowFactory.getRow(LampRowFactory.RowStyle.ONE_MINUTE);
	}
	
	public void show() {
		clockSource.generate();
		show(clockSource);
	}
	
	/**
	 * Shows the clockSource current time, according to the Berlin Clock characteristics, using the <code>Printer</code>
	 * @Param clockSource is the source of the clock
	 * */
	private void show(ClockSource clockSource){
		String printableSecondRow = getPrintableSecondRow(clockSource.getSecond());
		String printableFiveHourRow = getPrintableFiveHourRow(clockSource.getHour());
		String printableOneHourRow = getPrintableOneHourRow(clockSource.getHour());
		String printableFiveMinuteRow = getPrintableFiveMinuteRow(clockSource.getMinute());
		String printableOneMinuteRow = getPrintableOneMinuteRow(clockSource.getMinute());
		
		String delimiter = clockPosition == ClockPosition.HORIZONTAL ? " " : "\n";
		
		StringBuilder sb = new StringBuilder();
		//sb.append(clockSource.getHumanReadableFormat()).append(delimiter);
		sb.append(printableSecondRow).append(delimiter)
			.append(printableFiveHourRow).append(delimiter)
			.append(printableOneHourRow).append(delimiter)
			.append(printableFiveMinuteRow).append(delimiter)
			.append(printableOneMinuteRow);
		
		printer.print(sb.toString());
	}
	
	/**
	 * Obtains a string that represents the one minute row lamp by a given minute
	 * @param minute represents the minutes, between 0 and 59
	 * */
	protected String getPrintableOneMinuteRow(int minute) {
		checkMinute(minute);
		
		int position = minute % MINUTE_INTERVAL;
		return lightOn(oneMinuteLampRow, position);
	}

	/**
	 * Obtains a string that represents the five minute row lamp by a given minute
	 * @param minute represents the minutes, between 0 and 59
	 * */
	protected String getPrintableFiveMinuteRow(int minute) {
		checkMinute(minute);
		
		int position = minute / MINUTE_INTERVAL;
		return lightOn(fiveMinuteLampRow, position);
	}

	/**
	 * Obtains a string that represents the one hour row lamp by a given hour of the day
	 * @param hourOfDay represents the hour of a day, between 0 and 23
	 * */
	protected String getPrintableOneHourRow(int hourOfDay) {
		checkHour(hourOfDay);
		
		int position = hourOfDay % HOUR_INTERVAL;
		return lightOn(oneHourLampRow, position);
	}

	/**
	 * Obtains a string that represents the five hour row lamp by a given hour of the day
	 * @param hourOfDay represents the hour of a day, between 0 and 23
	 * */
	protected String getPrintableFiveHourRow(int hourOfDay) {
		checkHour(hourOfDay);
		
		int position = hourOfDay / HOUR_INTERVAL;
		return lightOn(fiveHourLampRow, position);
	}

	/**
	 * Obtains a string that represents the second row lamp by a given second
	 * @param second represents the second, between 0 and 59
	 * */
	protected String getPrintableSecondRow(int second) {
		int position = second % 4 >= 2 ? 0 : 1;
		return lightOn(secondLampRow, position);
	}
	
	private void checkHour(int hour){
		if(hour > 23 || hour < 0){
			throw new IllegalStateException("Invalid hour: " + hour);
		}
	}
	
	private void checkMinute(int minute){
		if(minute > 59 || minute < 0){
			throw new IllegalStateException("Invalid minute: " + minute);
		}
	}
	
	/**
	 * Lamps lights on
	 * @param lampRow is an instance of the one of the subclasses of <code>LampRow</code>
	 * @param lampPosition is the position of the last lamp to be lighted on, from left to right
	 * */
	protected String lightOn(LampRow lampRow, int lampPosition){
		List<LampColor> lamps = lampRow.getLamps();
		
		if(lamps.size() < lampPosition){
			throw new IllegalStateException("lampPosition cannot be greater than lampRow size: " + lamps.size()+ " < " + lampPosition);
		}
		
		StringBuilder printableRow = new StringBuilder(); 
		
		int lightedOn = 0;
		for(int i = 0; i < lampPosition; i++){
			printableRow.append(lamps.get(i));
			lightedOn++;
		}
		
		if(lightedOn < lamps.size()){
			for(int i = lightedOn; i < lamps.size(); i++){
				printableRow.append(lampRow.getOffColor());
			}
		}
		
		return printableRow.toString();
	}

	public void run() {
		while(true){
			show();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
