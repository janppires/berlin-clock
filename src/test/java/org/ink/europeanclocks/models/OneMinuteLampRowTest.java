package org.ink.europeanclocks.models;

import static org.junit.Assert.*;

import java.util.List;

import org.ink.europeanclocks.models.lamprow.LampRow;
import org.ink.europeanclocks.models.lamprow.LampRow.LampColor;
import org.ink.europeanclocks.models.lamprow.OneMinuteLampRow;
import org.junit.Test;

public class OneMinuteLampRowTest {

	@Test
	public void testCorrectInstatiation(){
		
		LampRow lampRow = new OneMinuteLampRow();
		assertEquals(4, lampRow.getRowSize());
		
		List<LampColor> lamps = lampRow.getLamps();
		assertEquals(LampColor.YELLOW, lamps.get(0));
		assertEquals(LampColor.YELLOW, lamps.get(1));
		assertEquals(LampColor.YELLOW, lamps.get(2));
		assertEquals(LampColor.YELLOW, lamps.get(3));
	}
}
