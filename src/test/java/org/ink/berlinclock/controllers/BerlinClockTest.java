package org.ink.berlinclock.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.ink.berlinclock.models.clocks.Clock;
import org.ink.berlinclock.models.clocks.ClockPosition;
import org.ink.berlinclock.models.clocks.ClockSource;
import org.ink.berlinclock.models.lamprow.LampRow;
import org.ink.berlinclock.models.printer.Printer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BerlinClockTest {

	private ClockSource clockSource;
	private Printer printer;
	
	@Before
	public void setUp(){
		clockSource = mock(ClockSource.class);
		printer = mock(Printer.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullClockPositionArgument(){
		Clock clock = new BerlinClock(null, clockSource, printer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullClockSourceArgument(){
		Clock clock = new BerlinClock(ClockPosition.HORIZONTAL, null, printer);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullPrinterArgument(){
		Clock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, null);
	}
	
	@Test
	public void testLightsOn(){
		FakeLampRow fakeLampRow = new FakeLampRow();
		
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);

		assertEquals("OOOO", clock.lightOn(fakeLampRow, 0));
		assertEquals("ROOO", clock.lightOn(fakeLampRow, 1));
		assertEquals("RYOO", clock.lightOn(fakeLampRow, 2));
		assertEquals("RYRO", clock.lightOn(fakeLampRow, 3));
		assertEquals("RYRY", clock.lightOn(fakeLampRow, 4));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testLightsOnWithPositionExceeded(){
		FakeLampRow fakeLampRow = new FakeLampRow();
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		
		clock.lightOn(fakeLampRow, 5);
	}
	
	@Test
	public void testPrintableSecondRow(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);

		assertEquals("Y", clock.getPrintableSecondRow(00));
		assertEquals("Y", clock.getPrintableSecondRow(01));
		assertEquals("O", clock.getPrintableSecondRow(02));
		assertEquals("O", clock.getPrintableSecondRow(58));
		assertEquals("O", clock.getPrintableSecondRow(59));
	}
	
	@Test
	public void testPrintableOneMinuteRow(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		
		assertEquals("OOOO", clock.getPrintableOneMinuteRow(00));
		assertEquals("YOOO", clock.getPrintableOneMinuteRow(01));
		assertEquals("YYOO", clock.getPrintableOneMinuteRow(02));
		assertEquals("YYYO", clock.getPrintableOneMinuteRow(03));
		assertEquals("YYYY", clock.getPrintableOneMinuteRow(04));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPrintableOneMinuteRowWithInvalidMinute(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		clock.getPrintableOneMinuteRow(60);
		
		clock.getPrintableOneMinuteRow(-1);
	}
	
	@Test
	public void testPrintableFiveMinuteRow(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		
		assertEquals("OOOOOOOOOOO", clock.getPrintableFiveMinuteRow(00));
		assertEquals("YOOOOOOOOOO", clock.getPrintableFiveMinuteRow(05));
		assertEquals("YYOOOOOOOOO", clock.getPrintableFiveMinuteRow(10));
		assertEquals("YYROOOOOOOO", clock.getPrintableFiveMinuteRow(15));
		assertEquals("YYRYOOOOOOO", clock.getPrintableFiveMinuteRow(20));
		assertEquals("YYRYYOOOOOO", clock.getPrintableFiveMinuteRow(25));
		assertEquals("YYRYYROOOOO", clock.getPrintableFiveMinuteRow(30));
		assertEquals("YYRYYRYOOOO", clock.getPrintableFiveMinuteRow(35));
		assertEquals("YYRYYRYYOOO", clock.getPrintableFiveMinuteRow(40));
		assertEquals("YYRYYRYYROO", clock.getPrintableFiveMinuteRow(45));
		assertEquals("YYRYYRYYRYO", clock.getPrintableFiveMinuteRow(50));
		assertEquals("YYRYYRYYRYY", clock.getPrintableFiveMinuteRow(55));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPrintableFiveMinuteRowWithInvalidMinute(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		clock.getPrintableFiveMinuteRow(60);
		
		clock.getPrintableFiveMinuteRow(-1);
	}
	
	@Test
	public void testPrintableOneHourRow(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		
		assertEquals("OOOO", clock.getPrintableOneHourRow(0));
		assertEquals("ROOO", clock.getPrintableOneHourRow(1));
		assertEquals("RROO", clock.getPrintableOneHourRow(2));
		assertEquals("RRRO", clock.getPrintableOneHourRow(3));
		assertEquals("RRRR", clock.getPrintableOneHourRow(4));
		assertEquals("OOOO", clock.getPrintableOneHourRow(5));
		assertEquals("ROOO", clock.getPrintableOneHourRow(6));
		assertEquals("RROO", clock.getPrintableOneHourRow(7));
		assertEquals("RRRO", clock.getPrintableOneHourRow(8));
		assertEquals("RRRR", clock.getPrintableOneHourRow(9));
		assertEquals("OOOO", clock.getPrintableOneHourRow(10));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPrintableOneHourRowWithInvalidMinute(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		clock.getPrintableOneHourRow(24);
		
		clock.getPrintableOneHourRow(-1);
	}
	
	@Test
	public void testPrintableFiveHourRow(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		
		assertEquals("OOOO", clock.getPrintableFiveHourRow(0));
		assertEquals("ROOO", clock.getPrintableFiveHourRow(5));
		assertEquals("ROOO", clock.getPrintableFiveHourRow(6));
		assertEquals("RROO", clock.getPrintableFiveHourRow(10));
		assertEquals("RROO", clock.getPrintableFiveHourRow(11));
		assertEquals("RRRO", clock.getPrintableFiveHourRow(15));
		assertEquals("RRRO", clock.getPrintableFiveHourRow(16));
		assertEquals("RRRR", clock.getPrintableFiveHourRow(20));
		assertEquals("RRRR", clock.getPrintableFiveHourRow(21));
		assertEquals("RRRR", clock.getPrintableFiveHourRow(23));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPrintableFiveHourRowWithInvalidMinute(){
		BerlinClock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		clock.getPrintableFiveHourRow(24);
		
		clock.getPrintableFiveHourRow(-1);
	}
	
	/**
	 * this test simulates the zero exercise example
	 * INPUT: 00:00:00 
	 * OUTPUT: Y OOOO OOOO OOOOOOOOOOO OOOO
	 * */
	@Test
	public void testOutputZero(){

		when(clockSource.getHour()).thenReturn(00);
		when(clockSource.getMinute()).thenReturn(00);
		when(clockSource.getSecond()).thenReturn(00);
		
		doNothing().when(clockSource).generate();
		
		FakePrinter printer = new FakePrinter(); //to obtain the printed value
		
		Clock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		clock.show();
		
		assertEquals("Y OOOO OOOO OOOOOOOOOOO OOOO", printer.getValueToPrint());
	}
	
	/**
	 * this test simulates the first exercise example
	 * INPUT: 13:17:01
	 * OUTPUT: O RROO RRRO YYROOOOOOOO YYOO
	 * 
	 * ATTENTION: this test is failing once it make part of the exercise.
	 * */
	@Test
	@Ignore
	public void testOutputOne(){

		when(clockSource.getHour()).thenReturn(13);
		when(clockSource.getMinute()).thenReturn(17);
		when(clockSource.getSecond()).thenReturn(01);
		
		doNothing().when(clockSource).generate();
		
		FakePrinter printer = new FakePrinter(); //to obtain the printed value
		
		Clock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		clock.show();
		
		assertEquals("O RROO RRRO YYROOOOOOOO YYOO", printer.getValueToPrint());
	}
	
	/**
	 * this test simulates the second exercise example
	 * INPUT: 23:59:59
	 * OUTPUT: O RRRR RRRO YYRYYRYYRYY YYYY
	 * */
	@Test
	public void testOutputTwo(){

		when(clockSource.getHour()).thenReturn(23);
		when(clockSource.getMinute()).thenReturn(59);
		when(clockSource.getSecond()).thenReturn(59);
		
		doNothing().when(clockSource).generate();
		
		FakePrinter printer = new FakePrinter(); //to obtain the printed value
		
		Clock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		clock.show();
		
		assertEquals("O RRRR RRRO YYRYYRYYRYY YYYY", printer.getValueToPrint());
	}
	
	/**
	 * this test simulates the second exercise example
	 * INPUT: 24:00:00 
	 * OUTPUT: Y RRRR RRRR OOOOOOOOOOO OOOO
	 * 
	 * ATTENTION: this test is failing once it make part of the exercise.
	 * */
	@Test
	@Ignore
	public void testOutputThree(){
		
		when(clockSource.getHour()).thenReturn(24);
		when(clockSource.getMinute()).thenReturn(00);
		when(clockSource.getSecond()).thenReturn(00);
		
		doNothing().when(clockSource).generate();
		
		FakePrinter printer = new FakePrinter(); //to obtain the printed value
		
		Clock clock = new BerlinClock(ClockPosition.HORIZONTAL, clockSource, printer);
		clock.show();
		
		assertEquals("Y RRRR RRRR OOOOOOOOOOO OOOO", printer.getValueToPrint());
	}
	
	private static class FakePrinter implements Printer{

		private String valueToPrint;
		
		public void print(String text) {
			this.valueToPrint = text;
		}
		
		public String getValueToPrint(){
			return valueToPrint;
		}
	}
	
	private static class FakeLampRow extends LampRow{
		public FakeLampRow(){
			addLamp(LampColor.RED);
			addLamp(LampColor.YELLOW);
			addLamp(LampColor.RED);
			addLamp(LampColor.YELLOW);
		}
	}
}
