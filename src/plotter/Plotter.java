package plotter;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.time.Second;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import controller.Controller;

public class Plotter
{
	ChartPanel chartPanel;
	Controller controller;

	public Plotter()
	{
        
    }

	public ChartPanel nImpression(ArrayList<Integer> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("Number of Impressions");
        for(int j = 0 ; j < i.size() ; j++)
        {
        	
        	//System.out.println(i.get(j));
	        first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "Number of Impressions");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nClicks(ArrayList<Integer> i,  int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("Number of Clicks");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "Number of Clicks");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nUniques(ArrayList<Integer> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("Number of Uniques");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "Number of Uniques");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nBounces(ArrayList<Integer> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("Number of Bounces");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "Number of Bounces");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nConversions(ArrayList<Integer> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("Number of Conversions");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "Number of Conversions");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel totalCost(ArrayList<Float> i, int time, long startTimeMiliS) 
	{
		TimeSeries first = new TimeSeries("Total Cost");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "Total Cost");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCTR(ArrayList<Float> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("CTR");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "CTR");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPA(ArrayList<Float> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("CPA");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "CPA");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPC(ArrayList<Float> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("CPC");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "CPC");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPM(ArrayList<Float> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("CPM");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j));
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "CPM");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel bounceRate(ArrayList<Float> i, int time, long startTimeMiliS) 
	{
        TimeSeries first = new TimeSeries("Bounce Rate");
        
        for(int j = 0 ; j < i.size() ; j++)
        {
        	first.add(new Second(new Date(startTimeMiliS + time * j * 1000)), i.get(j).doubleValue());
        }
        TimeSeriesCollection data = new TimeSeriesCollection(first);
        JFreeChart chart = createChart(data, "Bounce Rate");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

	public ChartPanel clickCost(ArrayList<Float> dd) 
	{
		double[] dList = new double[dd.size()];
		for(int i = 0; i < dd.size(); i++)
		{
			dList[i] = (double) dd.get(i);
		}
		HistogramDataset hd = new HistogramDataset();
		hd.setType(HistogramType.FREQUENCY);
		hd.addSeries("Click Cost", dList, 5);
		JFreeChart chart = ChartFactory.createHistogram("Click Cost Histogram", 
				"Cost of Click (GB Pence)", 
				"Frequency", 
				hd, 
				PlotOrientation.VERTICAL, 
				false, 
				false, 
				false);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;

    }	
	
	 private JFreeChart createChart(XYDataset dataSet, String title)
	 {	        
		 JFreeChart chart = ChartFactory.createXYLineChart(title + " Chart",
		            "Time(s)", 
		            title, 
		            dataSet,
		            PlotOrientation.VERTICAL,
		            true,
		            true,
		            false);
		 chart.setBackgroundPaint(Color.WHITE);
		 
		 return chart;
	 }
	 
	 private JFreeChart createChart(TimeSeriesCollection dataSet, String title)
	 {	        
		
		 JFreeChart chart = ChartFactory.createTimeSeriesChart(title + " Chart",
		            "Time(s)", 
		            title, 
		            dataSet,
		            true,
		            true,
		            false);
		 chart.setBackgroundPaint(Color.WHITE);
		 XYPlot plot = chart.getXYPlot();
		 DateAxis axis = (DateAxis) plot.getDomainAxis();
		 axis.setDateFormatOverride(new SimpleDateFormat("dd-MM HH:mm")); 
		 
		 return chart;
	 }


}