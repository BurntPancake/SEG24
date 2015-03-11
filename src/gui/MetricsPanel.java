package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import controller.Controller;

class MetricsPanel extends JPanel{
	
	private Controller controller;
	private JButton calculateButton;
	private JTable metricTable, costTable;
	private JLabel[] impressionNumber, clickNumber, uniqueNumber, bounceNumber,
					 conversionNumber, ctr, cpa, cpc, cpm, bounceRate, totalCost;
	
	final int PER_HOUR = 1, PER_DAY = 2, NO_OF_IMPRESSIONS = 0, NO_OF_CLICKS = 1,
			NO_OF_UNIQUES = 2, NO_OF_BOUNCES = 3, NO_OF_CONVERSIONS = 4, CTR = 5,
			CPA = 6, CPC = 7, CPM = 8, BOUNCE_RATE = 9;
	
	MetricsPanel(Controller controller){
		this.controller = controller;
		this.calculateButton = new JButton("Calculate Metrics");
		
		GridLayout gl = new GridLayout(0, 2);
		this.setLayout(gl);
		
		Object[] metricColumnNames = {
				"Metrics",
				"Per Hour",
				"Per Day"
		};
		
		Object[] costColumnNames = {
				"Total Campaign Cost of",
				"Amount (GBP)"
		};
		
		Object[][] metricData = {
				{ 	"Number of Impressions:", "0", "0"	},
				{ 	"Number of Clicks:", "0", "0"	},
				{ 	"Number of Uniques:", "0", "0"	},
				{ 	"Number of Bounces:", "0", "0"	},
				{ 	"Number of Conversions:", "0", "0"	},
				{ 	"Click-through rate (%):", "0", "0"	},
				{ 	"Cost-per-aquisition (GB Pence):", "0", "0"	},
				{ 	"Cost-per-click (GB Pence):", "0", "0"	},
				{ 	"Cost-per-thousand-impressions (GB Pence):", "0", "0"	},
				{ 	"Bounce Rate (%):", "0", "0"	}
		};
		
		Object[][] costData = {
				{	"Impressions", "0"	},
				{	"Clicks",	"0"			}
		};
		
		MetricTableModel metricModel = new MetricTableModel(metricData, metricColumnNames);
		metricTable = new JTable(metricModel);
		
		MetricTableModel costModel = new MetricTableModel(costData, costColumnNames);
		costTable = new JTable(costModel);
		
		TableColumn column = null;
		
		column = metricTable.getColumnModel().getColumn(0);
		column.setPreferredWidth(300);
		column = metricTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(75);
		column = metricTable.getColumnModel().getColumn(2);
		column.setPreferredWidth(75);
		
		column = costTable.getColumnModel().getColumn(0);
		column.setPreferredWidth(150);
		column = costTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(150);
		
		Dimension dim = new Dimension(550, 182);
		JScrollPane sp = new JScrollPane(metricTable);
		sp.setSize(dim);
		sp.setMaximumSize(dim);
		sp.setMinimumSize(dim);
		sp.setPreferredSize(dim);
		
		dim = new Dimension(320, 54);
		JScrollPane costPane = new JScrollPane(costTable);
		costPane.setSize(dim);
		costPane.setMaximumSize(dim);
		costPane.setMinimumSize(dim);
		costPane.setPreferredSize(dim);
		
		this.add(sp);
	
		JPanel rightPanel = new JPanel();
		GridBagLayout rl = new GridBagLayout();
		rightPanel.setLayout(rl);
		
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 2;
		rightPanel.add(costPane, cons);
		
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(0, 0, 0, 10);
		rightPanel.add(new OptionPanel(this.controller, this), cons);
		rightPanel.revalidate();
		
		this.add(rightPanel);
		this.calculateButton.addActionListener(new MetricListener(this.controller, this));

		this.revalidate();

	}
	
	public void displayMetrics(String[] metrics) {
		
		metricTable.setValueAt(metrics[0], NO_OF_IMPRESSIONS, PER_HOUR);
		metricTable.setValueAt(metrics[1], NO_OF_IMPRESSIONS, PER_DAY);
		metricTable.setValueAt(metrics[2], NO_OF_CLICKS, PER_HOUR);
		metricTable.setValueAt(metrics[3], NO_OF_CLICKS, PER_DAY);
		metricTable.setValueAt(metrics[4], NO_OF_UNIQUES, PER_HOUR);
		metricTable.setValueAt(metrics[5], NO_OF_UNIQUES, PER_DAY);
		metricTable.setValueAt(metrics[6], NO_OF_BOUNCES, PER_HOUR);
		metricTable.setValueAt(metrics[7], NO_OF_BOUNCES, PER_DAY);
		metricTable.setValueAt(metrics[8], NO_OF_CONVERSIONS, PER_HOUR);
		metricTable.setValueAt(metrics[9], NO_OF_CONVERSIONS, PER_DAY);
		metricTable.setValueAt(metrics[12], CTR, PER_HOUR);
		metricTable.setValueAt(metrics[13], CTR, PER_DAY);
		metricTable.setValueAt(metrics[14], CPA, PER_HOUR);
		metricTable.setValueAt(metrics[15], CPA, PER_DAY);
		metricTable.setValueAt(metrics[16], CPC, PER_HOUR);
		metricTable.setValueAt(metrics[17], CPC, PER_DAY);
		metricTable.setValueAt(metrics[18], CPM, PER_HOUR);
		metricTable.setValueAt(metrics[19], CPM, PER_DAY);
		metricTable.setValueAt(metrics[20], BOUNCE_RATE, PER_HOUR);
		metricTable.setValueAt(metrics[21], BOUNCE_RATE, PER_DAY);
		costTable.setValueAt(metrics[10], 0, 1);
		costTable.setValueAt(metrics[11], 1, 1);	
	}
	
	public void updateBounceNumber(String[] metrics)
	{
		metricTable.setValueAt(metrics[0], NO_OF_BOUNCES, PER_HOUR);
		metricTable.setValueAt(metrics[1], NO_OF_BOUNCES, PER_DAY);
	}
	
	public void updateBounceRate(String[] metrics)
	{
		metricTable.setValueAt(metrics[0], BOUNCE_RATE, PER_HOUR);
		metricTable.setValueAt(metrics[1], BOUNCE_RATE, PER_DAY);
	}
	
}

class MetricListener implements ActionListener {

	private Controller controller;
	private MetricsPanel mp;
	
	public MetricListener(Controller controller, MetricsPanel mp) {
		this.controller = controller;
		this.mp = mp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] metrics = controller.calculateMetrics();
		mp.displayMetrics(metrics);
	}	
}


