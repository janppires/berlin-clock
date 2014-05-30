package org.ink.europeanclocks.models;

import static org.junit.Assert.*;

import java.util.List;

import org.ink.europeanclocks.models.lamprow.LampRow;
import org.ink.europeanclocks.models.lamprow.LampRow.LampColor;
import org.ink.europeanclocks.models.lamprow.TwoSecondLampRow;
import org.junit.Test;

public class TwoSecondLampRowTest {

	@Test
	public void testCorrectInstatiation(){
		
		LampRow lampRow = new TwoSecondLampRow();
		assertEquals(1, lampRow.getRowSize());
		
		List<LampColor> lamps = lampRow.getLamps();
		assertEquals(LampColor.YELLOW, lamps.get(0));
	}
}
