package controller;

import filter.Filter;
import gui.GUI;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import decoder.Click;
import decoder.Decoder;
import decoder.DecoderInterface;
import decoder.Impression;
import decoder.Server;
import calculator.Calculator;
import plotter.Plotter;
import sorter.RecordSorter;

public class Controller {

	private DecoderInterface decoder;
	private Calculator calc;
	private Plotter plotter;
	private String bounceNumberPreference, bounceRatePreference;
	
	private Filter filter = new Filter();
	private ArrayList<Impression> impressionRecords;
	private ArrayList<Server> serverRecords;
	private ArrayList<Click> clickRecords;
	
	private ArrayList<Impression> originalImpressionRecords;
	private ArrayList<Server> originalServerRecords;
	private ArrayList<Click> originalClickRecords;
	
	private Calculator originalCalc;

	public Controller(DecoderInterface decoder, Plotter plotter) {
		this.decoder = decoder;
		this.plotter = plotter;
		this.bounceNumberPreference = "Pages";
		this.bounceRatePreference = "Pages";
	}
	
	public void setFileLocation(String impressionLogLocation, String clickLogLocation, String serverLogLocation){
		try {
			impressionRecords = decoder.getImpressionLogData(impressionLogLocation);
			serverRecords = decoder.getServerLogData(serverLogLocation);
			clickRecords = decoder.getClickLogData(clickLogLocation);
			
			originalImpressionRecords = impressionRecords;
			originalServerRecords = serverRecords;
			originalClickRecords = clickRecords;
			
			/**
			RecordSorter rs = new RecordSorter();
			rs.sortRecords(impressionRecords, "Date");
			rs.sortRecords(serverRecords, "Entry Date");
			rs.sortRecords(clickRecords, "Date");*/
			
			this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
			this.originalCalc = calc;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void resetFilterOptions()
	{
		impressionRecords = originalImpressionRecords;
		clickRecords = originalClickRecords;
		serverRecords = originalServerRecords;
		this.calc = originalCalc;
	}
	
	public void clearIDs()
	{
		HashSet<Long> idList = new HashSet<Long>(impressionRecords.size());
		for(int i = 0; i < impressionRecords.size(); i++)
		{
			idList.add(impressionRecords.get(i).id);
		}
		
		clickRecords = filter.filterClickTablebyID(clickRecords, idList);
		serverRecords = filter.filterServerTablebyID(serverRecords, idList);
	}
	
	public void setContext(ArrayList<String> contexts)
	{
		impressionRecords = filter.filterTablebyField(impressionRecords, "Context", contexts);
		this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
	}
	
	public void setAgeRange(ArrayList<String> ranges)
	{
		impressionRecords = filter.filterTablebyField(impressionRecords, "Age", ranges);
		this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
	}
	
	public void setIncomeRange(ArrayList<String> ranges)
	{
		impressionRecords = filter.filterTablebyField(impressionRecords, "Income", ranges);
		this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
	}
	
	public void setGender(ArrayList<String> genders)
	{
		impressionRecords = filter.filterTablebyField(impressionRecords, "Gender", genders);
		this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
	}
	
	public void SetDateRange(String startDate, String endDate)
	{
		impressionRecords = filter.filterImpTableByTimeInterval(impressionRecords, startDate, endDate);
		serverRecords = filter.filterServerTableByTimeInterval(serverRecords, startDate, endDate);
		clickRecords = filter.filterClickTableByTimeInterval(clickRecords, startDate, endDate);
		this.calc = new Calculator(impressionRecords, clickRecords, serverRecords);
	}
	
	public String[] getUpdatedMetrixBounceNumber()
	{
		String[] metrics = new String[2];
		if (bounceNumberPreference.equals("Pages")) {
			metrics[0] = Integer.toString(Math.round(getMeanInteger(calc.getBounceNumberByPages(3600, 2))));
			metrics[1] = Integer.toString(Math.round(getMeanInteger(calc.getBounceNumberByPages(86400, 2))));
		} else {
			metrics[0] = Integer.toString(Math.round(getMeanInteger(calc.getBounceNumberByTime(3600, 120))));
			metrics[1] = Integer.toString(Math.round(getMeanInteger(calc.getBounceNumberByTime(86400, 120))));
		}
		
		return metrics;
	}
	
	public String[] getUpdatedMetrixBounceRate()
	{
		DecimalFormat df = new DecimalFormat("###,###.##");
		
		String[] metrics = new String[2];
		if (bounceRatePreference.equals("Pages")) {
			metrics[0] = df.format(100 * getMeanFloat(calc.getBounceRateByPages(3600, 2)));
			metrics[1] = df.format(100 * getMeanFloat(calc.getBounceRateByPages(86400, 2)));
		} else {
			metrics[0] = df.format(100 * getMeanFloat(calc.getBounceRateByTime(3600, 120)));
			metrics[1] = df.format(100 * getMeanFloat(calc.getBounceRateByTime(86400, 120)));
		}
		
		return metrics;
	}
	
	public String[] calculateMetrics() {
		
		String[] metrics = new String[22];
		
		metrics[0] = Integer.toString(Math.round(getMeanInteger(calc.getImpressionNumber(3600))));
		metrics[1] = Integer.toString(Math.round(getMeanInteger(calc.getImpressionNumber(86400))));
		metrics[2] = Integer.toString(Math.round(getMeanInteger(calc.getClickNumber(3600))));
		metrics[3] = Integer.toString(Math.round(getMeanInteger(calc.getClickNumber(86400))));
		metrics[4] = Integer.toString(Math.round(getMeanInteger(calc.getUniqueNumber(3600))));
		metrics[5] = Integer.toString(Math.round(getMeanInteger(calc.getUniqueNumber(86400))));
		if (bounceNumberPreference.equals("Pages")) {
			metrics[6] = Integer.toString(Math.round(getMeanInteger(calc.getBounceNumberByPages(3600, 2))));
			metrics[7] = Integer.toString(Math.round(getMeanInteger(calc.getBounceNumberByPages(86400, 2))));
		} else {
			metrics[6] = Integer.toString(Math.round(getMeanInteger(calc.getBounceNumberByTime(3600, 120))));
			metrics[7] = Integer.toString(Math.round(getMeanInteger(calc.getBounceNumberByTime(86400, 120))));
		}
		metrics[8] = Integer.toString(Math.round(getMeanInteger(calc.getConversionNumber(3600))));
		metrics[9] = Integer.toString(Math.round(getMeanInteger(calc.getConversionNumber(86400))));
		
		DecimalFormat df = new DecimalFormat("###,###.##");
		
		float totalCost = 0;
		ArrayList<Float> impressionCost = calc.getImpressionCost(86400);
		
		for (Float imp : impressionCost)
			totalCost = totalCost + imp;
		
		metrics[10] = df.format(totalCost / 100);
	
		totalCost = 0;
		ArrayList<Float> clickCost = calc.getClickCost(86400);
		
		for (Float click : clickCost)
			totalCost = totalCost + click;
		
		metrics[11] = df.format(totalCost / 100);
		
		df = new DecimalFormat("###,###.#");
		
		DecimalFormat cpm = new DecimalFormat("#.######");
		
		metrics[12] = df.format(100 * getMeanFloat(calc.getCTR(3600)));
		metrics[13] = df.format(100 * getMeanFloat(calc.getCTR(86400)));
		metrics[14] = df.format(getMeanFloat(calc.getCPA(3600)));
		metrics[15] = df.format(getMeanFloat(calc.getCPA(86400)));
		metrics[16] = df.format(getMeanFloat(calc.getCPC(3600)));
		metrics[17] = df.format(getMeanFloat(calc.getCPC(86400)));
		metrics[18] = cpm.format(getMeanFloat(calc.getCPM(3600)));
		metrics[19] = cpm.format(getMeanFloat(calc.getCPM(86400)));
		if (bounceRatePreference.equals("Pages")) {
			metrics[20] = df.format(100 * getMeanFloat(calc.getBounceRateByPages(3600, 2)));
			metrics[21] = df.format(100 * getMeanFloat(calc.getBounceRateByPages(86400, 2)));
		} else {
			metrics[20] = df.format(100 * getMeanFloat(calc.getBounceRateByTime(3600, 120)));
			metrics[21] = df.format(100 * getMeanFloat(calc.getBounceRateByTime(86400, 120)));
		}
		System.out.println("****Done****");
		return metrics;
	}
	
	private float getMeanInteger(ArrayList<Integer> values) {
		int limit = values.size();
		int total = 0;
		
		for (int i = 0; i < limit; ++i) {
			total = total + values.get(i);
		}
		
		float mean = total / limit;
		return mean;
	}
	
	private float getMeanFloat(ArrayList<Float> values) 
	{
		int limit = values.size();
		float total = 0;
		
		for (int i = 0; i < limit; ++i) {
			total = total + values.get(i);
		}
		
		float mean = total / limit;
		return mean;
	}
	
	public ArrayList<Integer> getImpressionNumber(int interval)
	{
		ArrayList<Integer> data = calc.getImpressionNumber(interval);
		return data;
	}
	
	public ArrayList<Integer> getClicks(int interval)
	{
		ArrayList<Integer> data = calc.getClickNumber(interval);
		return data;
	}
	
	public ArrayList<Integer> getUniques(int interval)
	{
		ArrayList<Integer> data = calc.getUniqueNumber(interval);
		return data;
	}
	
	public ArrayList<Integer> getBounces(int interval)
	{
		ArrayList<Integer> data = calc.getBounceNumberByPages(interval, 2);
		return data;
	}
	
	public ArrayList<Integer> getConversions(int interval)
	{
		ArrayList<Integer> data = calc.getConversionNumber(interval);
		return data;
	}
	
	public ArrayList<Float> getTotalImpressionCost(int interval)
	{
		//ArrayList<Float> data = calc.getClickCost(interval);
		ArrayList<Float> data = calc.getImpressionCost(interval);
		return data;
	}
	
	public ArrayList<Float> getCTR(int interval)
	{
		ArrayList<Float> data = calc.getCTR(interval);
		return data;
	}
	
	public ArrayList<Float> getCPA(int interval)
	{
		ArrayList<Float> data = calc.getCPA(interval);
		return data;
	}
	
	public ArrayList<Float> getCPC(int interval)
	{
		ArrayList<Float> data = calc.getCPC(interval);
		return data;
	}
	
	public ArrayList<Float> getCPM(int interval)
	{
		ArrayList<Float> data = calc.getCPM(interval);
		return data;
	}
	
	public ArrayList<Float> getBounceRate(int interval)
	{
		ArrayList<Float> data = calc.getBounceRateByPages(interval, 2);
		return data;
	}
	
	public ArrayList<Float> getClickCost()
	{
		ArrayList<Float> temp = calc.getHistogramClicks();
		ArrayList<Float> vals = new ArrayList<Float>(temp.size());
		for (int i = 0; i < temp.size(); i++) {
			vals.add(temp.get(i));
		}
		
		return vals;
	}
	

}
