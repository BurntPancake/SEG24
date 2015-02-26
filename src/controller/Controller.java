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

	public Controller(DecoderInterface decoder, Plotter plotter) {
		this.decoder = decoder;
		this.plotter = plotter;
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
		
		metrics[0] = Float.toString(getMean(calc.getImpressionNumber(60)));
		metrics[1] = Float.toString(getMean(calc.getImpressionNumber(3600)));
		metrics[2] = Float.toString(getMean(calc.getImpressionNumber(86400)));
		metrics[3] = Float.toString(getMean(calc.getClickNumber(60)));
		metrics[4] = Float.toString(getMean(calc.getClickNumber(3600)));
		metrics[5] = Float.toString(getMean(calc.getClickNumber(86400)));
		System.out.println("getting unique 60");
		metrics[6] = Float.toString(getMean(calc.getUniqueNumber(60)));
		System.out.println(metrics[6]);
		System.out.println("getting unique 3600");
		metrics[7] = Float.toString(getMean(calc.getUniqueNumber(3600)));
		System.out.println(metrics[7]);
		metrics[8] = Float.toString(getMean(calc.getUniqueNumber(86400)));
		//metrics[6] = "filler";
		//metrics[7] = "filler";
		//metrics[8] = "filler";
		
		System.out.println("getting bounce by pages");
		metrics[9] = Float.toString(getMean(calc.getBounceNumberByPages(60, 2)));
		metrics[10] = Float.toString(getMean(calc.getBounceNumberByPages(3600, 2)));
		metrics[11] = Float.toString(getMean(calc.getBounceNumberByPages(86400, 2)));
		System.out.println("getting conversion number");
		metrics[12] = Float.toString(getMean(calc.getConversionNumber(60)));
		metrics[13] = Float.toString(getMean(calc.getConversionNumber(3600)));
		metrics[14] = Float.toString(getMean(calc.getConversionNumber(86400)));
		
		float totalCost = 0;
				
		Float[] impressionCost = calc.getImpressionCost(86400);
		
		for (Float imp : impressionCost) {
			totalCost = totalCost + imp;
		}
		System.out.println(Float.toString(getMean(calc.getImpressionNumber(60))));
		Float[] clickCost = calc.getClickCost(86400);
		
		for (Float click : clickCost)
			totalCost = totalCost + click;
		System.out.println(Float.toString(getMean(calc.getImpressionNumber(60))));
		metrics[15] = Float.toString(totalCost);
		
		metrics[16] = Float.toString(getMean(calc.getCTR(60)));
		for(float f : calc.getCTR(60))
			System.out.println(f);
		metrics[17] = Float.toString(getMean(calc.getCTR(3600)));
		metrics[18] = Float.toString(getMean(calc.getCTR(86400)));
		System.out.println(metrics[16]);	
		metrics[19] = Float.toString(getMean(calc.getCPA(60)));
		metrics[20] = Float.toString(getMean(calc.getCPA(3600)));
		metrics[21] = Float.toString(getMean(calc.getCPA(86400)));
		System.out.println(metrics[19]);
		metrics[22] = Float.toString(getMean(calc.getCPC(60)));
		metrics[23] = Float.toString(getMean(calc.getCPC(3600)));
		metrics[24] = Float.toString(getMean(calc.getCPC(86400)));
		System.out.println(Float.toString(getMean(calc.getImpressionNumber(60))));
		metrics[25] = Float.toString(getMean(calc.getCPM(60)));
		metrics[26] = Float.toString(getMean(calc.getCPM(3600)));
		metrics[27] = Float.toString(getMean(calc.getCPM(86400)));
		System.out.println(Float.toString(getMean(calc.getImpressionNumber(60))));
		metrics[28] = Float.toString(getMean(calc.getBounceRateByPages(60, 2)));
		metrics[29] = Float.toString(getMean(calc.getBounceRateByPages(3600, 2)));
		metrics[30] = Float.toString(getMean(calc.getBounceRateByPages(86400, 2)));
		
		int c = 0;
		for(String f : metrics)
		{
			System.out.println(c + " " + f);
			c++;
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
		for (int i : data)
		{
			System.out.println(i);
		}
		return data;
	}
	
	public Integer[] getClicks(int interval)
	{
		Integer[] data = calc.getClickNumber(interval);
		for (int i : data)
		{
			System.out.println(i);
		}
		return data;
	}
	
	public Integer[] getUniques(int interval)
	{
		Integer[] data = calc.getUniqueNumber(interval);
		for (int i : data)
		{
			System.out.println(i);
		}
		return data;
	}
	
	public Integer[] getBounces(int interval)
	{
		Integer[] data = calc.getBounceNumberByPages(interval, 2);
		for (int i : data)
		{
			System.out.println(i);
		}
		return data;
	}
	
	public Integer[] getConversions(int interval)
	{
		Integer[] data = calc.getConversionNumber(interval);
		for (int i : data)
		{
			System.out.println(i);
		}
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
		for (Float i : data)
		{
			System.out.println(i);
		}
		return data;
	}
	
	public Float[] getCPA(int interval)
	{
		Float[] data = calc.getCPA(interval);
		for (Float i : data)
		{
			System.out.println(i);
		}
		return data;
	}
	
	public Float[] getCPC(int interval)
	{
		Float[] data = calc.getCPC(interval);
		for (Float i : data)
		{
			System.out.println(i);
		}
		return data;
	}
	
	public Float[] getCPM(int interval)
	{
		Float[] data = calc.getCPM(interval);
		for (Float i : data)
		{
			System.out.println(i);
		}
		return data;
	}
	
	public Float[] getBounceRate(int interval)
	{
		Float[] data = calc.getBounceRateByPages(interval, 2);
		for (Float i : data)
		{
			System.out.println(i);
		}
		return data;
	}
}
