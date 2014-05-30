package org.ink.berlinclock.controllers;

import org.ink.berlinclock.models.printer.Printer;

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
