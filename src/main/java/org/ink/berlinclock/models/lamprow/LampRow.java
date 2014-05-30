package org.ink.berlinclock.models.lamprow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @class LampRow
 * 
 * @brief This class represents an immutable object class. Each instance contains an ordered list of lamps.
 * */
public abstract class LampRow {

	public static enum LampColor{
		RED("R"),
		YELLOW("Y"),
		OFF("O");
		private String colorDescription;
		
		private LampColor(String color){
			this.colorDescription = color;
		}
		
		@Override
		public String toString() {
			return colorDescription;
		}
	}
	
	/**
	 * Represents a list of colored lamps
	 */
	private List<LampColor> lamps;

	protected void addLamp(LampColor lc){
		if(lamps == null){
			lamps = new ArrayList<LampRow.LampColor>();
		}
		lamps.add(lc);
	}
	
	protected void setLamps(List<LampColor> lamps){
		this.lamps = lamps;
	}
	
	public int getRowSize(){
		return lamps.size();
	}
	
	public List<LampColor> getLamps(){
		return Collections.unmodifiableList(lamps);
	}
	
	protected List<LampColor> getSameColorLampList(LampColor lc, int lampsCount){
		List<LampColor> lampList = new ArrayList<LampColor>();
		for(int i = 0; i<lampsCount; i++){
			lampList.add(lc);	
		}
		return lampList;
	}
	
	public LampColor getOffColor(){
		return LampColor.OFF;
	}
}
