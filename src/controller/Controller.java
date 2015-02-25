package controller;

import java.util.Hashtable;

import decoder.Decoder;
import decoder.DecoderInterface;
import calculator.Calculator;
import calculator.CalculatorInterface;
import plotter.Plotter;

public class Controller {

	private DecoderInterface decoder;
	private CalculatorInterface calc;
	private Plotter plotter;

	public Controller(DecoderInterface decoder, Plotter plotter) {
		this.decoder = decoder;
		this.plotter = plotter;
	}
	
	public void setFileLocation(String impressionLogLocation, String clickLogLocation, String serverLogLocation){
		Hashtable<String, String>[] impressionRecords = decoder.getData(impressionLogLocation);
		Hashtable<String, String>[] serverRecords = decoder.getData(serverLogLocation);
		Hashtable<String, String>[] clickRecords = decoder.getData(clickLogLocation);
		this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
	}
	
	
	
}
