package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import plotter.Plotter;
import controller.Controller;

class ChartsPanel extends JPanel
{
	private String[] list = {"Click to Access Charts", "Number of Impressions", "Number of Clicks",
			"Number of Uniques", "Number of Bounces", "Number of Conversions", 
			"Total Cost", "CTR", "CPA", "CPC", "CPM", "Bounce Rate", "Click Cost"};
	
	ChartsPanel(Controller controller) 
	{
		Plotter plotter = new Plotter();	
		this.setLayout(new GridBagLayout());	
		GridBagConstraints cons = new GridBagConstraints();
		
		JLabel intervalLabel = new JLabel("Time Interval (s):");
		JTextField timeIntervalField = new JTextField();
		timeIntervalField.setText("60");
		
		final JComboBox<String> l = new JComboBox<String>();
		for(int i = 0 ; i < list.length ; i++)
		{
			l.addItem(list[i]);
		}		
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.insets = new Insets(0, 5, 0, 5);
		this.add(l, cons);
		
		cons.gridx = 1;
		this.add(intervalLabel, cons);
		
		cons.gridx = 2;
		this.add(timeIntervalField, cons);
		
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 3;
		cons.gridheight = 3;
		JPanel chartDisplayPanel = new JPanel();
		chartDisplayPanel.setPreferredSize(new Dimension(500, 250));
		this.add(chartDisplayPanel, cons);
		
		chartDisplayPanel.setLayout(new GridLayout());
		
		l.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				if (l.getItemAt(0).equals("Click to Access Charts"))
					l.removeItemAt(0);
					
				
				String x = (String) l.getSelectedItem();
				chartDisplayPanel.removeAll();
				switch (x)
				{	
		            case "Number of Impressions":	
		            	chartDisplayPanel.add(plotter.nImpression(controller.getImpressionNumber(60), 60));
		            	break;
		            	
		            case "Number of Clicks":
		            	chartDisplayPanel.add(plotter.nClicks(controller.getClicks(60), 60));
		            	break;
		            	
		            case "Number of Uniques":	
		            	chartDisplayPanel.add(plotter.nUniques(controller.getUniques(60), 60));
		            	break;
		            	
		            case "Number of Bounces":
		            	chartDisplayPanel.add(plotter.nBounces(controller.getBounces(60), 60));
		            	break;
		            	
		            case "Number of Conversions":
		            	chartDisplayPanel.add(plotter.nConversions(controller.getConversions(60), 60));
		            	break;
		            	/*
		            case "Total Cost":
		            	chartDisplayPanel.add(plotter.totalCost(controller.getTotalCost(60), 60));
		            	break; */
		            	
		            case "CTR":
		            	chartDisplayPanel.add(plotter.nCTR(controller.getCTR(60), 60));
		            	break;
		            	
		            case "CPA":
		            	chartDisplayPanel.add(plotter.nCPA(controller.getCPA(60), 60));
		            	break;
		            	
		            case "CPC":
		            	chartDisplayPanel.add(plotter.nCPC(controller.getCPC(60), 60));
		            	break;
		            	
		            case "CPM":
		            	chartDisplayPanel.add(plotter.nCPM(controller.getCPM(60), 60));
		            	break;
		            	
		            case "Bounce Rate":
		            	chartDisplayPanel.add(plotter.bounceRate(controller.getBounceRate(60), 60));
		            	break;
		            	
		            case "Click Cost":
		            	chartDisplayPanel.add(plotter.clickCost(controller.getClickCost(60), 60));
		            	break;
				}
				chartDisplayPanel.revalidate();
            	chartDisplayPanel.repaint();
            	ChartsPanel.this.revalidate();
            	ChartsPanel.this.repaint();
			}
		});		
	}
}
