package controller;

import gui.TestGUI;

import java.io.IOException;
import java.util.Hashtable;

import decoder.Decoder;
import decoder.DecoderInterface;
import calculator.Calculator;
import calculator.CalculatorInterface;
import plotter.Plotter;
import sorter.RecordSorter;

public class Controller {

	private DecoderInterface decoder;
	private CalculatorInterface calc;
	private Plotter plotter;
	private String bounceNumberPreference, bounceRatePreference;

	public Controller(DecoderInterface decoder, Plotter plotter) {
		this.decoder = decoder;
		this.plotter = plotter;
		this.bounceNumberPreference = "Pages";
		this.bounceRatePreference = "Pages";
	}
	
	public void setFileLocation(String impressionLogLocation, String clickLogLocation, String serverLogLocation){
		try {
			Hashtable<String, String>[] impressionRecords = decoder.getData(impressionLogLocation);
			Hashtable<String, String>[] serverRecords = decoder.getData(serverLogLocation);
			Hashtable<String, String>[] clickRecords = decoder.getData(clickLogLocation);
			
			/**
			RecordSorter rs = new RecordSorter();
			rs.sortRecords(impressionRecords, "Date");
			rs.sortRecords(serverRecords, "Entry Date");
			rs.sortRecords(clickRecords, "Date");*/
			
			this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] calculateMetrics() {
		
		String[] metrics = new String[31];
		
		metrics[0] = Float.toString(getMean(calc.getImpressionNumber(3600)));
		metrics[1] = Float.toString(getMean(calc.getImpressionNumber(86400)));
		metrics[2] = Float.toString(getMean(calc.getClickNumber(3600)));
		metrics[3] = Float.toString(getMean(calc.getClickNumber(86400)));
		metrics[4] = Float.toString(getMean(calc.getUniqueNumber(3600)));
		metrics[5] = Float.toString(getMean(calc.getUniqueNumber(86400)));
		if (bounceNumberPreference.equals("Pages")) {
			metrics[6] = Float.toString(getMean(calc.getBounceNumberByPages(3600, 2)));
			metrics[7] = Float.toString(getMean(calc.getBounceNumberByPages(86400, 2)));
		} else {
			metrics[6] = Float.toString(getMean(calc.getBounceNumberByTime(3600, 120)));
			metrics[7] = Float.toString(getMean(calc.getBounceNumberByTime(86400, 120)));
		}
		metrics[8] = Float.toString(getMean(calc.getConversionNumber(3600)));
		metrics[9] = Float.toString(getMean(calc.getConversionNumber(86400)));
		
		float totalCost = 0;
		Float[] impressionCost = calc.getImpressionCost(86400);
		
		for (Float imp : impressionCost)
			totalCost = totalCost + imp;
		
		metrics[10] = Float.toString(totalCost);
	
		totalCost = 0;
		Float[] clickCost = calc.getClickCost(86400);
		
		for (Float click : clickCost)
			totalCost = totalCost + click;
		
		metrics[11] = Float.toString(totalCost);
		metrics[12] = Float.toString(getMean(calc.getCTR(3600)));
		metrics[13] = Float.toString(getMean(calc.getCTR(86400)));
		metrics[14] = Float.toString(getMean(calc.getCPA(3600)));
		metrics[15] = Float.toString(getMean(calc.getCPA(86400)));
		metrics[16] = Float.toString(getMean(calc.getCPC(3600)));
		metrics[17] = Float.toString(getMean(calc.getCPC(86400)));
		metrics[18] = Float.toString(getMean(calc.getCPM(3600)));
		metrics[19] = Float.toString(getMean(calc.getCPM(86400)));
		if (bounceRatePreference.equals("Pages")) {
			metrics[20] = Float.toString(getMean(calc.getBounceRateByPages(3600, 2)));
			metrics[21] = Float.toString(getMean(calc.getBounceRateByPages(86400, 2)));
		} else {
			metrics[20] = Float.toString(getMean(calc.getBounceRateByTime(3600, 120)));
			metrics[21] = Float.toString(getMean(calc.getBounceRateByTime(86400, 120)));
		}
		
		return metrics;
	}
	
	private float getMean(Integer[] values) {
		int limit = values.length;
		int total = 0;
		
		for (int i = 0; i < limit; ++i) {
			total = total + values[i];
		}
		
		float mean = total / limit;
		return mean;
	}
	
	private float getMean(Float[] values) {
		int limit = values.length;
		float total = 0;
		
		for (int i = 0; i < limit; ++i) {
			total = total + values[i];
		}
		
		float mean = total / limit;
		return mean;
	}
	
	public Integer[] getImpressionNumber(int interval)
	{
		Integer[] data = calc.getImpressionNumber(interval);
		return data;
	}
	
	public Integer[] getClicks(int interval)
	{
		Integer[] data = calc.getClickNumber(interval);
		return data;
	}
	
	public Integer[] getUniques(int interval)
	{
		Integer[] data = calc.getUniqueNumber(interval);
		return data;
	}
	
	public Integer[] getBounces(int interval)
	{
		Integer[] data = calc.getBounceNumberByPages(interval, 2);
		return data;
	}
	
	public Integer[] getConversions(int interval)
	{
		Integer[] data = calc.getConversionNumber(interval);
		return data;
	}
	
	/*public Integer[] getTotalCost(int interval)
	{
		Integer[] data = calc.
		for (int i : data)
		{
			System.out.println(i);
		}
		return data;
	}*/
	
	public Float[] getCTR(int interval)
	{
		Float[] data = calc.getCTR(interval);
		return data;
	}
	
	public Float[] getCPA(int interval)
	{
		Float[] data = calc.getCPA(interval);
		return data;
	}
	
	public Float[] getCPC(int interval)
	{
		Float[] data = calc.getCPC(interval);
		return data;
	}
	
	public Float[] getCPM(int interval)
	{
		Float[] data = calc.getCPM(interval);
		return data;
	}
	
	public Float[] getBounceRate(int interval)
	{
		Float[] data = calc.getBounceRateByPages(interval, 2);
		return data;
	}
	
	public void setBouncePreferences(String numberPreference, String ratePreference) {
		if (numberPreference.equals("Pages")) {
			bounceNumberPreference = "Pages";
		} else if (numberPreference.equals("Time")) {
			bounceNumberPreference = "Time";
		}
		
		if (ratePreference.equals("Pages")) {
			bounceRatePreference = "Pages";
		} else if (ratePreference.equals("Time")) {
			bounceRatePreference = "Time";
		}
		
	}
}
