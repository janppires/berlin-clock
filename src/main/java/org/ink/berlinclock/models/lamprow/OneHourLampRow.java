package org.ink.berlinclock.models.lamprow;

public class OneHourLampRow extends LampRow{

	public OneHourLampRow(){
		setLamps(getSameColorLampList(LampColor.RED, 4));
	}
}