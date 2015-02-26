package plotter;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Plotter extends JPanel
{
	ChartPanel chartPanel;

	public Plotter()
	{
        super(); 
        this.setVisible(true);
    }

	public ChartPanel nImpression(Integer[] i, int time) 
	{
        XYSeries first = new XYSeries("Numer of Impression");
        for(int j = 0 ; j < i.length ; j++)
        {
        	//System.out.println(i[j]);
	        first.add(time * j, i[j]);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Numer of Impression");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nClicks(Integer[] i) 
	{
        XYSeries first = new XYSeries("Numer of Clicks");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Numer of Clicks");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nUniques(Integer[] i) 
	{
        XYSeries first = new XYSeries("Numer of uniques");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Numer of uniques");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nBounces(Integer[] i) 
	{
        XYSeries first = new XYSeries("Numer of Bounces");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Numer of Bounces");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nConversions(Integer[] i) 
	{
        XYSeries first = new XYSeries("Numer of Conversions");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Numer of Conversions");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel totalCost(Integer[] i) 
	{
        XYSeries first = new XYSeries("Total Cost");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Total Cost");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCTR(Integer[] i) 
	{
        XYSeries first = new XYSeries("CTR");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "CTR");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPA(Integer[] i) 
	{
        XYSeries first = new XYSeries("CPA");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "CPA");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPC(Integer[] i) 
	{
        XYSeries first = new XYSeries("CPC");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "CPC");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel nCPM(Integer[] i) 
	{
        XYSeries first = new XYSeries("CPM");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "CPM");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	public ChartPanel bounceRate(Integer[] i) 
	{
        XYSeries first = new XYSeries("Bounce Rate");
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Bounce Rate");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	 private JFreeChart createChart(XYDataset dataSet, String title)
	 {	        
		 JFreeChart chart = ChartFactory.createXYLineChart(title,
		            "Time", 
		            "Title", 
		            dataSet,
		            PlotOrientation.VERTICAL,
		            true,
		            true,
		            false);
		 chart.setBackgroundPaint(Color.WHITE);
		 
		 return chart;
	 }
	
	 /*
	public static void main(String[] args)
	{
		Plotter demo = new Plotter("Title");
        demo.pack();
        demo.setVisible(true);

	}*/

}