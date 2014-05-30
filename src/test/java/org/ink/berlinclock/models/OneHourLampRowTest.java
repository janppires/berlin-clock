package org.ink.berlinclock.models;

import static org.junit.Assert.*;

import java.util.List;

import org.ink.berlinclock.models.lamprow.LampRow;
import org.ink.berlinclock.models.lamprow.LampRow.LampColor;
import org.ink.berlinclock.models.lamprow.OneHourLampRow;
import org.junit.Test;

public class OneHourLampRowTest {

	@Test
	public void testCorrectInstatiation(){
		
		LampRow lampRow = new OneHourLampRow();
		assertEquals(4, lampRow.getRowSize());
		
		List<LampColor> lamps = lampRow.getLamps();
		assertEquals(LampColor.RED, lamps.get(0));
		assertEquals(LampColor.RED, lamps.get(1));
		assertEquals(LampColor.RED, lamps.get(2));
		assertEquals(LampColor.RED, lamps.get(3));
	}
}
