package org.ink.europeanclocks.controllers;

import org.ink.europeanclocks.models.printer.Printer;

/**
 * <code>ConsolePrinter</code> is a implementation of
 * <code>Printer</code> and allow print text to console.
 * 
 * */
public class ConsolePrinter implements Printer{

	public void print(String text) {
		System.out.println(text);
	}
}
