package gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import plotter.Plotter;
import controller.Controller;

class ChartsPanel extends JPanel
{
	private String[] list = {"Click to Access Charts", "Number of Impressions", "Number of Clicks",
			"Number of Uniques", "Number of Bounces", "Number of Conversions", 
			"Total Impression Cost", "CTR", "CPA", "CPC", "CPM", "Bounce Rate", "Click Cost"};
	
	private JComboBox<String> timeIntervalMenu;
	final JComboBox<String> listOfCharts = new JComboBox<String>();
	JPanel chartDisplayPanel;
	Plotter plotter;
	Controller controller;
	
	private int getInterval()
	{
		String chosenInterval = (String) timeIntervalMenu.getSelectedItem();
		int chosenIntervalSeconds;
		if (chosenInterval.equals("1 hour")) {
			chosenIntervalSeconds = 3600;
		} else if (chosenInterval.equals("6 hours")) {
			chosenIntervalSeconds = 21600;
		} else {
			chosenIntervalSeconds = 86400;
		}
		return chosenIntervalSeconds;
	}
	
	ChartsPanel(Controller controller) 
	{
		plotter = new Plotter();
		this.controller = controller;
		this.setLayout(new GridBagLayout());	
		GridBagConstraints cons = new GridBagConstraints();
		
		JLabel intervalLabel = new JLabel("Time Interval:");
		
		String[] intervalSelection = {"1 hour", "6 hours", "24 hours"};
		timeIntervalMenu = new JComboBox<String>(intervalSelection);
		
		JButton newWindowButton = new JButton("New Chart Window");
		newWindowButton.addActionListener(new WindowListener());
		
		for(int i = 0 ; i < list.length ; i++)
		{
			listOfCharts.addItem(list[i]);
		}		
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.insets = new Insets(0, 5, 0, 5);
		this.add(listOfCharts, cons);
		
		cons.gridx = 2;
		this.add(intervalLabel, cons);
		
		cons.gridx = 3;
		this.add(timeIntervalMenu, cons);
		
		cons.gridx = 1;
		this.add(newWindowButton, cons);
		
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 3;
		cons.gridheight = 3;
		chartDisplayPanel = new JPanel();
		chartDisplayPanel.setPreferredSize(new Dimension(500, 250));
		this.add(chartDisplayPanel, cons);
		
		chartDisplayPanel.setLayout(new GridLayout());
		
		listOfCharts.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				if (listOfCharts.getItemAt(0).equals("Click to Access Charts"))
					listOfCharts.removeItemAt(0);
					
				updateChart(chartDisplayPanel);
			}
		});
		
		
		timeIntervalMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				updateChart(chartDisplayPanel);
			}
		});
	}
	
	public void updateChart(JPanel updatePanel){
		
		String x = (String) listOfCharts.getSelectedItem();
		updatePanel.removeAll();
		Long impStart = controller.getImpStartTime().getTime();
		Long clickStart = controller.getClickStartTime().getTime();
		Long serverStart = controller.getServerStartTime().getTime();
		
		switch (x)
		{	
			case "Click to Access Charts":
				break;
				
            case "Number of Impressions":	
            	updatePanel.add(plotter.nImpression(controller.getImpressionNumber(getInterval()), getInterval(), impStart));
            	break;
            	
            case "Number of Clicks":
            	updatePanel.add(plotter.nClicks(controller.getClicks(getInterval()), getInterval(), clickStart));
            	break;
            	
            case "Number of Uniques":	
            	updatePanel.add(plotter.nUniques(controller.getUniques(getInterval()), getInterval(), clickStart));
            	break;
            	
            case "Number of Bounces":
            	updatePanel.add(plotter.nBounces(controller.getBounces(getInterval()), getInterval(), serverStart));
            	break;
            	
            case "Number of Conversions":
            	updatePanel.add(plotter.nConversions(controller.getConversions(getInterval()), getInterval(), serverStart));
            	break;
            	
            case "Total Impression Cost":
            	updatePanel.add(plotter.totalCost(controller.getTotalImpressionCost(getInterval()), getInterval(), impStart));
            	break; 
            	
            case "CTR":
            	updatePanel.add(plotter.nCTR(controller.getCTR(getInterval()), getInterval(), clickStart));
            	break;
            	
            case "CPA":
            	updatePanel.add(plotter.nCPA(controller.getCPA(getInterval()), getInterval(), clickStart));
            	break;
            	
            case "CPC":
            	updatePanel.add(plotter.nCPC(controller.getCPC(getInterval()), getInterval(), clickStart));
            	break;
            	
            case "CPM":
            	updatePanel.add(plotter.nCPM(controller.getCPM(getInterval()), getInterval(), clickStart));
            	break;
            	
            case "Bounce Rate":
            	updatePanel.add(plotter.bounceRate(controller.getBounceRate(getInterval()), getInterval(), serverStart));
            	break;
            	
            case "Click Cost":
            	updatePanel.add(plotter.clickCost(controller.getClickCost()));
            	break;
		}
		
		updatePanel.revalidate();
		updatePanel.repaint();
    	ChartsPanel.this.revalidate();
    	ChartsPanel.this.repaint();
		
	}
	
	class WindowListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			JDialog dialog = new JDialog();
        	JPanel chartDisplayPanelPopUp = new JPanel();
        	ChartsPanel.this.updateChart(chartDisplayPanelPopUp);
        	dialog.add(chartDisplayPanelPopUp);
        	dialog.pack();
        	dialog.setVisible(true);
		
		}
		
	}
	
}
