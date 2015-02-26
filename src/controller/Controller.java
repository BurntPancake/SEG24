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
			
			RecordSorter rs = new RecordSorter();
			rs.sortRecords(impressionRecords, "Date");
			rs.sortRecords(serverRecords, "Entry Date");
			rs.sortRecords(clickRecords, "Date");
			
			this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] calculateMetrics() {
		String[] metrics = new String[28];
		//bounce rate
		
		Integer[] impressionNumber = calc.getImpressionNumber(60);
		metrics[0] = Float.toString(getMean(impressionNumber));
		
		impressionNumber = calc.getImpressionNumber(3600);
		metrics[1] = Float.toString(getMean(impressionNumber));
		
		impressionNumber = calc.getImpressionNumber(86400);
		metrics[2] = Float.toString(getMean(impressionNumber));
		
		Integer[] clickNumber = calc.getClickNumber(60);
		metrics[3] = Float.toString(getMean(clickNumber));
		
		clickNumber = calc.getClickNumber(3600);
		metrics[4] = Float.toString(getMean(clickNumber));
		
		clickNumber = calc.getClickNumber(86400);
		metrics[5] = Float.toString(getMean(clickNumber));
		
		Integer[] uniqueNumber = calc.getUniqueNumber(60);
		metrics[6] = Float.toString(getMean(uniqueNumber));
		
		uniqueNumber = calc.getUniqueNumber(3600);
		metrics[7] = Float.toString(getMean(uniqueNumber));
		
		uniqueNumber = calc.getUniqueNumber(86400);
		metrics[8] = Float.toString(getMean(uniqueNumber));
		
		Integer[] bounceNumber = calc.getBounceNumberByPages(60, 2);
		metrics[6] = Float.toString(getMean(bounceNumber));
		
		bounceNumber = calc.getBounceNumberByPages(3600, 2);
		metrics[7] = Float.toString(getMean(bounceNumber));
		
		bounceNumber = calc.getBounceNumberByPages(86400, 2);
		metrics[8] = Float.toString(getMean(bounceNumber));
		
		Integer[] conversionNumber = calc.getConversionNumber(60);
		metrics[9] = Float.toString(getMean(conversionNumber));
		
		conversionNumber = calc.getConversionNumber(3600);
		metrics[10] = Float.toString(getMean(conversionNumber));
		
		conversionNumber = calc.getConversionNumber(86400);
		metrics[11] = Float.toString(getMean(conversionNumber));
		
		Float[] impressionCost = calc.getImpressionCost(60);
		Float[] clickCost = calc.getClickCost(60);
		
		float totalCost = 0;
		
		for (Float imp : impressionCost)
			totalCost = totalCost + imp;
		
		for (Float click : clickCost)
			totalCost = totalCost + click;
		
		metrics[12] = Float.toString(totalCost);
		
		Float[] ctrNumber = calc.getCTR(60);
		metrics[13] = Float.toString(getMean(ctrNumber));
		
		ctrNumber = calc.getCTR(3600);
		metrics[14] = Float.toString(getMean(ctrNumber));
		
		ctrNumber = calc.getCTR(86400);
		metrics[15] = Float.toString(getMean(ctrNumber));
		
		Float[] cpaNumber = calc.getCPA(60);
		metrics[16] = Float.toString(getMean(cpaNumber));
		
		cpaNumber = calc.getCPA(3600);
		metrics[17] = Float.toString(getMean(cpaNumber));
		
		cpaNumber = calc.getCPA(86400);
		metrics[18] = Float.toString(getMean(cpaNumber));
		
		Float[] cpcNumber = calc.getCPC(60);
		metrics[19] = Float.toString(getMean(cpcNumber));
		
		cpcNumber = calc.getCPC(3600);
		metrics[20] = Float.toString(getMean(cpcNumber));
		
		cpcNumber = calc.getCPC(86400);
		metrics[21] = Float.toString(getMean(cpcNumber));
		
		Float[] cpmNumber = calc.getCPM(60);
		metrics[22] = Float.toString(getMean(cpmNumber));
		
		cpmNumber = calc.getCPM(3600);
		metrics[23] = Float.toString(getMean(cpmNumber));
		
		cpmNumber = calc.getCPM(86400);
		metrics[24] = Float.toString(getMean(cpmNumber));
		
		Float[] bounceRateNumber = calc.getBounceRateByPages(60, 2);
		metrics[25] = Float.toString(getMean(bounceRateNumber));
		
		bounceRateNumber = calc.getBounceRateByPages(3600, 2);
		metrics[26] = Float.toString(getMean(bounceRateNumber));
		
		bounceRateNumber = calc.getBounceRateByPages(86400, 2);
		metrics[27] = Float.toString(getMean(bounceRateNumber));
		
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
	
}
