package org.ink.europeanclocks.controllers;

import org.ink.europeanclocks.models.lamprow.FiveHourLampRow;
import org.ink.europeanclocks.models.lamprow.FiveMinuteLampRow;
import org.ink.europeanclocks.models.lamprow.LampRow;
import org.ink.europeanclocks.models.lamprow.OneHourLampRow;
import org.ink.europeanclocks.models.lamprow.OneMinuteLampRow;
import org.ink.europeanclocks.models.lamprow.TwoSecondLampRow;

/**
 * @class LampRowFactory
 * 
 * @brief This class implements the Factory pattern to handle with different row lamps.
 */
public class LampRowFactory {

	public static enum RowStyle{
		FIVE_HOUR,
		ONE_HOUR,
		FIVE_MINUTE,
		ONE_MINUTE,
		TWO_SECOND;
	}
	
	/**
	 * @param rowStyle identifies the row's characteristic
	 * @return a row of lamps according to the input parameter
	 * */
	public static LampRow getRow(RowStyle rowStyle){
		
		switch(rowStyle){
		case FIVE_HOUR: return new FiveHourLampRow();
		case ONE_HOUR: return new OneHourLampRow();
		case FIVE_MINUTE: return new FiveMinuteLampRow();
		case ONE_MINUTE: return new OneMinuteLampRow();
		case TWO_SECOND: return new TwoSecondLampRow();
		}
		
		throw new IllegalArgumentException("RowStyle unknown!");
	}
}
