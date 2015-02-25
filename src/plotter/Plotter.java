package plotter;

import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Plotter extends JFrame
{

	public Plotter(String chartTitle)
	{
        super();
        // This will create the dataset 
        XYDataset dataset = createDataset(chartTitle);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);
    }
	
	private  XYDataset createDataset(String name) 
	{
        XYSeries first = new XYSeries(name);
        first.add(5, 10);
        first.add(6, 8);
        first.add(7, 15);
        XYSeriesCollection data = new XYSeriesCollection(first);
        return data;
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
	
	public static void main(String[] args)
	{
		Plotter demo = new Plotter("Title");
        demo.pack();
        demo.setVisible(true);

	}

}
