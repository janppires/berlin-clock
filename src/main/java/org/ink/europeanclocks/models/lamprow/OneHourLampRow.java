package org.ink.europeanclocks.models.lamprow;

public class OneHourLampRow extends LampRow{

	public OneHourLampRow(){
		setLamps(getSameColorLampList(LampColor.RED, 4));
	}
}