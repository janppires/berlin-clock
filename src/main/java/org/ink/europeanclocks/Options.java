package org.ink.europeanclocks;

import org.ink.europeanclocks.models.clocks.ClockPosition;

/**
 * @class Options
 * 
 * @brief This class implements the Singleton pattern to handle
 *        command-line option processing.
 */
public class Options
{
	
	private boolean loadedSuccessfully;
	
    /** The singleton Options instance. */
    private static Options optionInstance = null;

    private ClockPosition clockPosition = ClockPosition.HORIZONTAL;
    private int clockWorkingTime = 15;

    /** Method to return the one and only singleton uniqueInstance. */
    public static Options instance()
    {
        if (optionInstance == null)
            optionInstance = new Options();

        return optionInstance;
    }

    public ClockPosition clockPosition(){
    	return clockPosition;
    }
    
    public int clockWorkingTime(){
    	return clockWorkingTime;
    }
 
	public boolean isLoadedSuccessfully() {
		return loadedSuccessfully;
	}

	private void setLoadedSuccessfully(boolean loadedSuccessfully) {
		this.loadedSuccessfully = loadedSuccessfully;
	}

    /**
     * Parse command-line arguments and set the appropriate values.
     */
    public void parseArgs(String argv[]){
        for (int argc = 0; argc < argv.length; argc += 2)
            if (argv[argc].equals("-p")){
            	if(!processClockPosition(argv[argc + 1])){
            		printUsage();
            		setLoadedSuccessfully(false);
            		return;
            	}
            }else if (argv[argc].equals("-t")){
            	if(!processClockWorkingTime(argv[argc + 1])){
            		printUsage();
            		setLoadedSuccessfully(false);
            		return;
            	}
            }else {
                printUsage();
                setLoadedSuccessfully(false);
                return;
            }

        setLoadedSuccessfully(true);
    }
    
    private boolean processClockPosition(String posArg){
    	if("horizontal".equals(posArg.toLowerCase())){
    		clockPosition = ClockPosition.HORIZONTAL;
    		return true;
    	}
    	
    	if("vertical".equals(posArg.toLowerCase())){
    		clockPosition = ClockPosition.VERTICAL;
    		return true;
    	}
    	
    	return false;
    }
    
    private boolean processClockWorkingTime(String posArg){
    	try{
    		clockWorkingTime = Integer.parseInt(posArg);
    	}catch(NumberFormatException nfe){
    		return false;
    	}
    	
    	return true;
    }

    /** Print out usage */
    public void printUsage() {
        System.out.println(getPrintUsageHelperMessage());
    }
    
    public String getPrintUsageHelperMessage(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("Usage: \n-h: invoke help ").append("\n");
    	sb.append("-p clock position (e.g., \"horizonal\" or \"vertical\")").append("\n");
    	sb.append("-t clock working time in seconds");
    	return sb.toString();
    }
}