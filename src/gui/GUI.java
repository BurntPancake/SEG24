package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

import controller.Controller;
import plotter.Plotter;//Wrong architecture!

public class GUI {

	private Controller controller;
	
	public GUI(Controller controller){
		this.controller = controller;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				display();
			}
		});
	}
		
	private void display(){
		
		JFrame frame = new JFrame();
		frame.setTitle("Ad Auction Dashboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel dataPane = new DataPanel(this.controller);
		JPanel chartsPane = new ChartsPanel(this.controller);
//		JPanel metricsPane = new MetricsPanel(this.controller);
		OptionPanel optionPane = new OptionPanel(this.controller);
		optionPane.init();

		tabbedPane.addTab("Data", dataPane);
		tabbedPane.addTab("Charts", chartsPane);
//		tabbedPane.addTab("Metrics", metricsPane);
		tabbedPane.addTab("Options", optionPane);
		
		frame.setContentPane(tabbedPane);
		frame.setResizable(true);
	
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
}

class DataPanel extends JPanel{
	
	JButton submitButton;
	JButton clickButton;
	JButton impressionButton;
	JButton serverButton;
	JTextField clickField;
	JTextField impressionField;
	JTextField serverField;
	JTextField errorField;
	private JFileChooser fc;
	private GridBagConstraints gbc;
	private Controller controller;
	
	DataPanel(Controller controller){
		
		this.controller = controller;
		this.clickButton = new JButton("Choose Click Log");
		this.impressionButton = new JButton("Choose Impression Log");
		this.serverButton = new JButton("Choose Server Log");
		this.clickField = new JTextField(50);
		this.impressionField = new JTextField(50);
		this.serverField = new JTextField(50);
		this.errorField = new JTextField(50);
		this.submitButton = new JButton("Submit");
		
		this.clickField.setEditable(false);
		this.impressionField.setEditable(false);
		this.serverField.setEditable(false);
		this.errorField.setEditable(false);
		
		this.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.fc = new JFileChooser();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.clickButton, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		this.add(this.clickField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(this.impressionButton, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		this.add(this.impressionField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		this.add(this.serverButton, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		this.add(this.serverField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		this.add(this.submitButton, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		this.add(this.errorField, gbc);
		
		FileFinder ff = new FileFinder();
		//ff.setNameToSearch(fc.getCurrentDirectory().getAbsolutePath());
		ff.searchDirectory(fc.getCurrentDirectory(), "impression_log.csv", "click_log.csv", "server_log.csv");
		
		//int impfound = 0, clickfound = 0, serverfound = 0;
		
		for (String matched : ff.foundFiles){
			System.out.println("Found: " + matched);
		
			if (matched.endsWith("impression_log.csv")){
				//impfound++;
				this.impressionField.setText(matched);
			} else if (matched.endsWith("click_log.csv")){
				//clickfound++;
				this.clickField.setText(matched);
			} else if (matched.endsWith("server_log.csv")){
				//serverfound++;
				this.serverField.setText(matched);
			}
		}		
		
		
		this.clickButton.addActionListener(new DataListener(this.controller, this));
		this.impressionButton.addActionListener(new DataListener(this.controller, this));
		this.serverButton.addActionListener(new DataListener(this.controller, this));
		this.submitButton.addActionListener(new DataListener(this.controller, this));
		
	}
	
}

class MetricsPanel extends JPanel{
	
	private Controller controller;
	private JButton calculateButton;
	private JTable metricTable;
	private JLabel[] impressionNumber, clickNumber, uniqueNumber, bounceNumber,
					 conversionNumber, ctr, cpa, cpc, cpm, bounceRate, totalCost;
	
	final int PER_HOUR = 1, PER_DAY = 2, NO_OF_IMPRESSIONS = 0, NO_OF_CLICKS = 1,
			NO_OF_UNIQUES = 2, NO_OF_BOUNCES = 3, NO_OF_CONVERSIONS = 4, CTR = 5,
			CPA = 6, CPC = 7, CPM = 8, BOUNCE_RATE = 9, TOTAL_COST = 10;
	
	MetricsPanel(Controller controller){
		this.controller = controller;
		this.calculateButton = new JButton("Calculate Metrics");
		
		FlowLayout fl = new FlowLayout();
		this.setLayout(fl);
		
		Object[] columnNames = {
				"Metrics",
				"Per Hour",
				"Per Day"
		};
		
		Object[][] data = {
				{ 	"Number of Impressions:", "0", "0"	},
				{ 	"Number of Clicks:", "0", "0"	},
				{ 	"Number of Uniques:", "0", "0"	},
				{ 	"Number of Bounces:", "0", "0"	},
				{ 	"Number of Conversions:", "0", "0"	},
				{ 	"Click-through rate (%):", "0", "0"	},
				{ 	"Cost-per-aquisition (GB Pence):", "0", "0"	},
				{ 	"Cost-per-click (GB Pence):", "0", "0"	},
				{ 	"Cost-per-thousand-impressions (GB Pence):", "0", "0"	},
				{ 	"Bounce Rate (%):", "0", "0"	},
				{ 	"Total Cost (GBP £):", "0", "0"	}
		};
/*
		MetricTableModel tableModel = new MetricTableModel(data, columnNames);
		metricTable = new JTable(tableModel);
		
		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
		    column = metricTable.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(300); //third column is bigger
		    } else {
		        column.setPreferredWidth(75);
		    }
		}
		
		Dimension dim = new Dimension(550, 198);
		JScrollPane sp = new JScrollPane(metricTable);
		sp.setSize(dim);
		sp.setMaximumSize(dim);
		sp.setMinimumSize(dim);
		sp.setPreferredSize(dim);
		
		this.add(sp);
		this.revalidate();
	
		this.add(calculateButton);
		this.calculateButton.addActionListener(new MetricListener(this.controller, this));
		*/
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
		metricTable.setValueAt(metrics[10], TOTAL_COST, 1);
		metricTable.setValueAt(metrics[11], TOTAL_COST, 2);
		
	}
	
}

class DataListener implements ActionListener {

	private Controller controller;
	private JFileChooser fc;
	private DataPanel dp;
	
	public DataListener(Controller controller, DataPanel dp) {
		this.controller = controller;
		this.dp = dp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(dp.clickButton)){
			
			fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(dp);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				String filePath = fc.getSelectedFile().getAbsolutePath();
				dp.clickField.setText(filePath);
			}
		} else if (e.getSource().equals(dp.impressionButton)){
			
			fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(dp);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				String filePath = fc.getSelectedFile().getAbsolutePath();
				dp.impressionField.setText(filePath);
			}
		} else if (e.getSource().equals(dp.serverButton)){
			fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(dp);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				String filePath = fc.getSelectedFile().getAbsolutePath();
				dp.serverField.setText(filePath);
			}
		} else if (e.getSource().equals(dp.submitButton)){
			if (dp.impressionField.getText().equals("") || dp.clickField.getText().equals("") || dp.serverField.getText().equals("")){
				dp.errorField.setText("All three logs must be submitted");
			} else{
				dp.errorField.setText("");
				controller.setFileLocation(dp.impressionField.getText(), dp.clickField.getText(), dp.serverField.getText());
			}
		}
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

class ChartsPanel extends JPanel
{
	private String[] list = {"Click to Access Charts", "Number of Impression", "Number of Clicks",
			"Number of Uniques", "Number of Bounces", "Number of Conversions", 
			/*"Total Cost",*/ "CTR", "CPA", "CPC", "CPM", "Bounce Rate"};
	
	ChartsPanel(Controller controller) 
	{
		Plotter plotter = new Plotter();	
		this.setLayout(new BorderLayout());	
		
		final JComboBox l = new JComboBox();
		for(int i = 0 ; i < list.length ; i++)
		{
			l.addItem(list[i]);
		}		
		this.add(l , BorderLayout.NORTH);
		
		JPanel chartDisplayPanel = new JPanel();
		this.add(chartDisplayPanel, BorderLayout.CENTER);
		chartDisplayPanel.setLayout(new GridLayout());
		
		l.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{				
				String x = (String) l.getSelectedItem();
				chartDisplayPanel.removeAll();
				switch (x)
				{	
		            case "Number of Impression":	
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
		            	
		            /*case "Total Cost":
		            	ChartsPanel.this.add(plotter.nBounces(controller.getBounces(60), 60) , BorderLayout.CENTER);
		            	ChartsPanel.this.validate();
		            	ChartsPanel.this.repaint();
		            	break;*/
		            	
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
				}
				chartDisplayPanel.revalidate();
            	chartDisplayPanel.repaint();
            	ChartsPanel.this.revalidate();
            	ChartsPanel.this.repaint();
			}
		});		
	}
	
	
	
}

class SubmissionListener implements ActionListener {
	
	private JRadioButton bounceNumberPages, bounceNumberTime, bounceRatePages, bounceRateTime;
	private Controller controller;
	
	public SubmissionListener(JRadioButton bounceNumberPages, JRadioButton bounceNumberTime,
						      JRadioButton bounceRatePages, JRadioButton bounceRateTime, Controller controller) {
		this.bounceNumberPages = bounceNumberPages;
		this.bounceNumberTime = bounceNumberTime;
		this.bounceRatePages = bounceRatePages;
		this.bounceRateTime = bounceRateTime;
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {

		String numPref = "";
		String ratePref = "";
		
		if (bounceNumberPages.isSelected()) {
			numPref = "Pages";
		} else if (bounceNumberTime.isSelected()) {
			numPref = "Time";
		}
		
		if (bounceRatePages.isSelected()) {
			ratePref = "Pages";
		} else if (bounceRateTime.isSelected()) {
			ratePref = "Time";
		}
		
		controller.setBouncePreferences(numPref,  ratePref);
			
	}
	
}

class OptionPanel extends JPanel {
	
	private Controller controller;
	
	public OptionPanel(Controller controller) {
		this.controller = controller;
	}
	
	public void init() {
		
		GridLayout gl = new GridLayout(5, 0);
		this.setLayout(gl);
				
		JRadioButton bounceNumberByPages = new JRadioButton("Get bounce number by pages.", true);
		JRadioButton bounceNumberByTime = new JRadioButton("Get bounce number by time.", false);
		
		JRadioButton bounceRateByPages = new JRadioButton("Get bounce rate by pages.", true);
		JRadioButton bounceRateByTime = new JRadioButton("Get bounce rate by time.", false);
		
		ButtonGroup bounceNumberGroup = new ButtonGroup();
		bounceNumberGroup.add(bounceNumberByPages);
		bounceNumberGroup.add(bounceNumberByTime);
			
		ButtonGroup bounceRateGroup = new ButtonGroup();
		bounceRateGroup.add(bounceRateByPages);
		bounceRateGroup.add(bounceRateByTime);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new SubmissionListener(bounceNumberByPages, bounceNumberByTime, bounceRateByPages, bounceRateByTime, controller));
		
		this.add(bounceNumberByPages);
		this.add(bounceNumberByTime);
		this.add(bounceRateByPages);
		this.add(bounceRateByTime);
		this.add(submit);
		
	}
	
}