package org.ink.berlinclock.models.lamprow;

public class FiveHourLampRow extends LampRow{

	public FiveHourLampRow(){
		setLamps(getSameColorLampList(LampColor.RED, 4));
	}
}
