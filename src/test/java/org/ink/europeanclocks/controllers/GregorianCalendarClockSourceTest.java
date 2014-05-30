package org.ink.europeanclocks.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.GregorianCalendar;

import org.ink.europeanclocks.models.clocks.ClockSource;
import org.junit.Before;
import org.junit.Test;

public class GregorianCalendarClockSourceTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNullPatternArgument(){
		ClockSource clockSource = new GregorianCalendarClockSource(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyPatternArgument(){
		ClockSource clockSource = new GregorianCalendarClockSource("");
	}
	
	@Test
	public void testDefaultPattern(){
	    GregorianCalendar calendar = new GregorianCalendar(2014, 5, 30, 0, 20, 35);
	    
	    GregorianCalendarClockSource clockSource = new GregorianCalendarClockSource();
	    clockSource.setCalendar(calendar);
	    assertEquals("00:20:35", clockSource.getHumanReadableFormat());
	}
	
	@Test
	public void testTimeGetters(){
		GregorianCalendar calendar = new GregorianCalendar(2014, 5, 30, 0, 20, 35);
	    
		GregorianCalendarClockSource clockSource = new GregorianCalendarClockSource();
		clockSource.setCalendar(calendar);
	    
	    assertEquals(0, clockSource.getHour());
	    assertEquals(20, clockSource.getMinute());
	    assertEquals(35, clockSource.getSecond());
	}
}
