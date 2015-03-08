package controller;

import gui.GUI;

import java.io.IOException;
import java.text.DecimalFormat;
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
		
		String[] metrics = new String[22];
		
		metrics[0] = Integer.toString(Math.round(getMean(calc.getImpressionNumber(3600))));
		metrics[1] = Integer.toString(Math.round(getMean(calc.getImpressionNumber(86400))));
		metrics[2] = Integer.toString(Math.round(getMean(calc.getClickNumber(3600))));
		metrics[3] = Integer.toString(Math.round(getMean(calc.getClickNumber(86400))));
		metrics[4] = Integer.toString(Math.round(getMean(calc.getUniqueNumber(3600))));
		metrics[5] = Integer.toString(Math.round(getMean(calc.getUniqueNumber(86400))));
		if (bounceNumberPreference.equals("Pages")) {
			metrics[6] = Integer.toString(Math.round(getMean(calc.getBounceNumberByPages(3600, 2))));
			metrics[7] = Integer.toString(Math.round(getMean(calc.getBounceNumberByPages(86400, 2))));
		} else {
			metrics[6] = Integer.toString(Math.round(getMean(calc.getBounceNumberByTime(3600, 120))));
			metrics[7] = Integer.toString(Math.round(getMean(calc.getBounceNumberByTime(86400, 120))));
		}
		metrics[8] = Integer.toString(Math.round(getMean(calc.getConversionNumber(3600))));
		metrics[9] = Integer.toString(Math.round(getMean(calc.getConversionNumber(86400))));
		
		DecimalFormat df = new DecimalFormat("###,###.##");
		
		float totalCost = 0;
		Float[] impressionCost = calc.getImpressionCost(86400);
		
		for (Float imp : impressionCost)
			totalCost = totalCost + imp;
		
		metrics[10] = df.format(totalCost / 100);
	
		totalCost = 0;
		Float[] clickCost = calc.getClickCost(86400);
		
		for (Float click : clickCost)
			totalCost = totalCost + click;
		
		metrics[11] = df.format(totalCost / 100);
		
		df = new DecimalFormat("###,###.#");
		
		DecimalFormat cpm = new DecimalFormat("#.######");
		
		metrics[12] = df.format(100 * getMean(calc.getCTR(3600)));
		metrics[13] = df.format(100 * getMean(calc.getCTR(86400)));
		metrics[14] = df.format(getMean(calc.getCPA(3600)));
		metrics[15] = df.format(getMean(calc.getCPA(86400)));
		metrics[16] = df.format(getMean(calc.getCPC(3600)));
		metrics[17] = df.format(getMean(calc.getCPC(86400)));
		metrics[18] = cpm.format(getMean(calc.getCPM(3600)));
		metrics[19] = cpm.format(getMean(calc.getCPM(86400)));
		if (bounceRatePreference.equals("Pages")) {
			metrics[20] = df.format(100 * getMean(calc.getBounceRateByPages(3600, 2)));
			metrics[21] = df.format(100 * getMean(calc.getBounceRateByPages(86400, 2)));
		} else {
			metrics[20] = df.format(100 * getMean(calc.getBounceRateByTime(3600, 120)));
			metrics[21] = df.format(100 * getMean(calc.getBounceRateByTime(86400, 120)));
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
