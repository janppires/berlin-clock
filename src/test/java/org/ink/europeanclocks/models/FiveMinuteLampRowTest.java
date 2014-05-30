package org.ink.europeanclocks.models;

import static org.junit.Assert.*;

import java.util.List;

import org.ink.europeanclocks.models.lamprow.FiveMinuteLampRow;
import org.ink.europeanclocks.models.lamprow.LampRow;
import org.ink.europeanclocks.models.lamprow.LampRow.LampColor;
import org.junit.Test;

public class FiveMinuteLampRowTest {

	@Test
	public void testCorrectInstatiation(){
		
		LampRow lampRow = new FiveMinuteLampRow();
		assertEquals(11, lampRow.getRowSize());
		
		List<LampColor> lamps = lampRow.getLamps();
		assertEquals(LampColor.YELLOW, lamps.get(0));
		assertEquals(LampColor.YELLOW, lamps.get(1));
		assertEquals(LampColor.RED, lamps.get(2));
		assertEquals(LampColor.YELLOW, lamps.get(3));
		assertEquals(LampColor.YELLOW, lamps.get(4));
		assertEquals(LampColor.RED, lamps.get(5));
		assertEquals(LampColor.YELLOW, lamps.get(6));
		assertEquals(LampColor.YELLOW, lamps.get(7));
		assertEquals(LampColor.RED, lamps.get(8));
		assertEquals(LampColor.YELLOW, lamps.get(9));
		assertEquals(LampColor.YELLOW, lamps.get(10));
	}
}
