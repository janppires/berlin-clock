package org.ink.berlinclock.models.lamprow;

public class OneMinuteLampRow extends LampRow{

	public OneMinuteLampRow(){
		setLamps(getSameColorLampList(LampColor.YELLOW, 4));
	}
}