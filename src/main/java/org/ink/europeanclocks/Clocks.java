package org.ink.europeanclocks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.ink.europeanclocks.controllers.BerlinClock;
import org.ink.europeanclocks.controllers.ConsolePrinter;
import org.ink.europeanclocks.controllers.GregorianCalendarClockSource;
import org.ink.europeanclocks.models.clocks.Clock;
import org.ink.europeanclocks.models.clocks.ClockSource;
import org.ink.europeanclocks.models.printer.Printer;

/**
 *
 */
public class Clocks 
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException{
    	
    	/*Initializes the Options singleton.*/
    	Options.instance().parseArgs(args);
    	
    	if(!Options.instance().isLoadedSuccessfully()){
    		return;
    	}
    	
    	ClockSource clockSource = new GregorianCalendarClockSource(); //Initializes clock source
    	Printer printer = new ConsolePrinter(); //Initializes printer
    	Clock berlinClock = new BerlinClock(Options.instance().clockPosition(), clockSource, printer); //Initialize berlin clock
    	
    	/*create a executor with a single thread*/
    	ExecutorService executor = Executors.newSingleThreadExecutor();
    	executor.submit(berlinClock);
    	executor.shutdown();// Disable new tasks from being submitted
    	
    	if (!executor.awaitTermination(Options.instance().clockWorkingTime(), TimeUnit.SECONDS)) {
    		executor.shutdownNow(); // Cancel currently executing tasks
    	}
    	
    	executor.shutdownNow();
    	
    	System.out.print("Terminated!\n");
    }
}
