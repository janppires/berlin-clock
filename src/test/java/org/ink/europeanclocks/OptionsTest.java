package org.ink.europeanclocks;

import static org.junit.Assert.*;

import org.ink.europeanclocks.models.clocks.ClockPosition;
import org.junit.Before;
import org.junit.Test;

public class OptionsTest {

	private Options instance;
	
	private String printUsage = "Usage: \n-h: invoke help \n-p clock position (e.g., \"horizonal\" or \"vertical\"\n-t clock working time in seconds";

	@Before
	public void setUp(){
		instance = Options.instance();
	}
	
	@Test
	public void optionsInstanceTest() {
		assertNotNull("Options instance is null!", instance);
	}
	
	@Test
	public void optionsDefaultTest(){
		String[] argv = new String[]{};
		instance.parseArgs(argv);
		
		assertEquals(ClockPosition.HORIZONTAL, instance.clockPosition());
		assertEquals(15, instance.clockWorkingTime());
	}
	
	@Test
	public void testCorrectHelpOptionParameter(){
		String[] argv = new String[]{"-h"};
		instance.parseArgs(argv);
		assertFalse(instance.isLoadedSuccessfully());
	}
	
	@Test
	public void testInvalidOptionParameter(){
		String[] argv = new String[]{"-x", "3"};
		instance.parseArgs(argv);
		assertFalse(instance.isLoadedSuccessfully());
	}
	
	@Test
	public void testCorrectClockHorizontalPositionOptionParameter(){
		String[] argv = new String[]{"-p", "horizontal"};
		instance.parseArgs(argv);
		assertEquals(ClockPosition.HORIZONTAL, instance.clockPosition());
		assertTrue(instance.isLoadedSuccessfully());
	}
	
	@Test
	public void testCorrectClockVerticalPositionOptionParameter(){
		String[] argv = new String[]{"-p", "vertical"};
		instance.parseArgs(argv);
		assertEquals(ClockPosition.VERTICAL, instance.clockPosition());
		assertTrue(instance.isLoadedSuccessfully());
	}
	
	@Test
	public void testCorrectTimeOptionParameter(){
		String[] argv = new String[]{"-t", "7"};
		instance.parseArgs(argv);
		assertEquals(7, instance.clockWorkingTime());
		assertTrue(instance.isLoadedSuccessfully());
	}
	
	@Test
	public void testInvalidTimeOptionParameter(){
		String[] argv = new String[]{"-t", "foo"};
		instance.parseArgs(argv);
		assertFalse(instance.isLoadedSuccessfully());
	}
	
	@Test
	public void optionsPrintUsage(){
		assertEquals(printUsage, instance.getPrintUsageHelperMessage());
	}
}
