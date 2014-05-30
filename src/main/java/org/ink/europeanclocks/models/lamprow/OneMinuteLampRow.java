package org.ink.europeanclocks.models.lamprow;

public class OneMinuteLampRow extends LampRow{

	public OneMinuteLampRow(){
		setLamps(getSameColorLampList(LampColor.YELLOW, 4));
	}
}