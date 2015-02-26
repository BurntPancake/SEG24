package plotter;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import sun.applet.Main;
import controller.Controller;
import decoder.DecoderInterface;

public class Plotter extends JPanel
{
	Controller controller;
	ChartPanel chartPanel;
	DecoderInterface decoder;
	public Plotter(Controller controller, DecoderInterface decoder)
	{
		this.controller = controller;
		this.decoder = decoder;
	}
	public Plotter(String name)
	{
        super(); 
        switch (name)
		{
            case "Number of Impression":	
            chartPanel = nImpression();
            break;
            	
            case "Number of Clicks":
            chartPanel = nClicks();
            break;
            	
            case "Number of Uniques":	
            chartPanel = nUniques();
            break;
            	
            case "Number of Bounces":
            chartPanel = nBounces();
            break;
            	
            case "Number of Conversions":
            chartPanel = nConversions();
            break;
            	
            case "Total Cost":
            
            break;
            	
            case "Number of CTR":
            chartPanel = nCTR();
            break;
            	
            case "Number of CPA":
            chartPanel = nCPA();
            break;
            	
            case "CPC":
            chartPanel = nCPC();
            break;
            	
            case "CPM":
            chartPanel = nCPM();
            break;
            	
            case "Bounce Rate":
            chartPanel = bounceRate();
            break;
		}
        
        chartPanel.setSize(250, 150);
        this.add(chartPanel);
        this.setVisible(true);
    }
	
	private ChartPanel nImpression() 
	{
        XYSeries first = new XYSeries("Numer of Impression");
        System.out.println("222");
        Integer[] i = controller.getIValues();
        System.out.println("111");
        for(int j = 0 ; j < i.length ; j++)
        {
        	System.out.println(i[j]);
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Numer of Impression");
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
	
	private ChartPanel nClicks() 
	{
        XYSeries first = new XYSeries("Numer of Clicks");
        Integer[] i = controller.getClickValues();
        
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
	
	private ChartPanel nUniques() 
	{
        XYSeries first = new XYSeries("Numer of uniques");
        Integer[] i = controller.getUValues();
        
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
	
	private ChartPanel nBounces() 
	{
        XYSeries first = new XYSeries("Numer of Bounces");
        Integer[] i = controller.getBValues();
        
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
	
	private ChartPanel nConversions() 
	{
        XYSeries first = new XYSeries("Numer of Conversions");
        Integer[] i = controller.getCValues();
        
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
	
	/*private ChartPanel totalCost() 
	{
        XYSeries first = new XYSeries("Total Cost");
        Integer[] i = controller
        
        for(int j = 0 ; j < i.length ; j++)
        {
	        first.add(i[j].doubleValue(), 3600);
        }
        XYSeriesCollection data = new XYSeriesCollection(first);
        XYDataset dataSet = data;
        JFreeChart chart = createChart(dataSet, "Total Cost);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }*/
	
	private ChartPanel nCTR() 
	{
        XYSeries first = new XYSeries("CTR");
        Float[] i = controller.getctrValues();
        
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
	
	private ChartPanel nCPA() 
	{
        XYSeries first = new XYSeries("CPA");
        Float[] i = controller.getcpaValues();
        
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
	
	private ChartPanel nCPC() 
	{
        XYSeries first = new XYSeries("CPC");
        Float[] i = controller.getcpcValues();
        
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
	
	private ChartPanel nCPM() 
	{
        XYSeries first = new XYSeries("CPM");
        Float[] i = controller.getcpmValues();
        
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
	
	private ChartPanel bounceRate() 
	{
        XYSeries first = new XYSeries("Bounce Rate");
        Float[] i = controller.getBounceRateValues();
        
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
	
	/*public static void main(String[] args)
	{
		Plotter demo = new Plotter("Title");
        demo.pack();
        demo.setVisible(true);

	}*/

}