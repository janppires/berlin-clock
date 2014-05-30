package org.ink.berlinclock.models.lamprow;

public class TwoSecondLampRow extends LampRow{

	public TwoSecondLampRow(){
		setLamps(getSameColorLampList(LampColor.YELLOW, 1));
	}
}