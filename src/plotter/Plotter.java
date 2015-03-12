package plotter;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Plotter
{
	ChartPanel chartPanel;

	public Plotter()
	{
        
    }

	public ChartPanel nImpression(Integer[] i, int time) 
	{
        XYSeries first = new XYSeries("Number of Impressions");
        for(int j = 0 ; j < i.length ; j++)
        {
        	//System.out.println(i[j]);
	        first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Number of Impressions");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nClicks(Integer[] i,  int time) 
	{
        XYSeries first = new XYSeries("Number of Clicks");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Number of Clicks");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nUniques(Integer[] i, int time) 
	{
        XYSeries first = new XYSeries("Number of Uniques");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Number of Uniques");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nBounces(Integer[] i, int time) 
	{
        XYSeries first = new XYSeries("Number of Bounces");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Number of Bounces");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nConversions(Integer[] i, int time) 
	{
        XYSeries first = new XYSeries("Number of Conversions");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Number of Conversions");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel totalCost(Float[] i, int time) 
	{
		XYSeries first = new XYSeries("Total Cost");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Total Cost");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCTR(Float[] i, int time) 
	{
        XYSeries first = new XYSeries("CTR");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "CTR");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPA(Float[] i, int time) 
	{
        XYSeries first = new XYSeries("CPA");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "CPA");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPC(Float[] i, int time) 
	{
        XYSeries first = new XYSeries("CPC");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "CPC");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPM(Float[] i, int time) 
	{
        XYSeries first = new XYSeries("CPM");
        
        for(int j = 0 ; j < i.length ; j++)
        {
        	first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "CPM");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel bounceRate(Float[] i, int time) 
	{
        XYSeries first = new XYSeries("Bounce Rate");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(time * j, i[j].doubleValue());
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Bounce Rate");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel clickCost(Integer[] d) 
	{
		HistogramDataset data = new HistogramDataset();            
		double[] val = new double[d.length];
		for(int j = 0 ; j < d.length ; j++)
        {
			System.out.println(d[j]);
	        val[j] = d[j].doubleValue();
        }
		//HistogramBin bin = new HistogramBin(4,9);
		data.addSeries("Cost Distribution", val,  10);
        JFreeChart chart = createHistogram(data, "Click Cost");
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
	
	 private JFreeChart createHistogram(IntervalXYDataset dataSet, String title)
	 {	        
		 JFreeChart chart = ChartFactory.createHistogram(title + " Chart",
		            "", 
		            title, 
		            dataSet,
		            PlotOrientation.VERTICAL,
		            false,
		            true,
		            false);
		 
		 chart.setBackgroundPaint(Color.WHITE);
		 
		 return chart;
	 }

}