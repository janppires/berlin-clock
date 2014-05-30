package org.ink.berlinclock.models.clocks;

/**
 * <code>Clock</code> is an interface that extends <code>Runnable</code> to be used in an <code>Executor</code> implementation.
 * 
 * */
public interface Clock extends Runnable{

	/**
	 * Display the clock time
	 * */
	void show();
}
