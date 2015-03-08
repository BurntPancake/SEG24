package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import controller.Controller;
import plotter.Plotter;//Wrong architecture!

public class TestGUI {

	private Controller controller;
	
	public TestGUI(Controller controller){
		this.controller = controller;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				display();
			}
		});
	}
	
	/*
	 * Controlled by controller now.
	public static void main(String[] args){
		
			
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				display();
			}
		});
		
	}
	*/
	
	private void display(){
		
		JFrame frame = new JFrame();
		frame.setTitle("Ad Auction Dashboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel dataPane = new DataPanel(this.controller);
		JPanel chartsPane = new ChartsPanel(this.controller);
		JPanel metricsPane = new MetricsPanel(this.controller);
		OptionPanel optionPane = new OptionPanel(this.controller);
		optionPane.init();

		tabbedPane.addTab("Data", dataPane);
		tabbedPane.addTab("Charts", chartsPane);
		tabbedPane.addTab("Metrics", metricsPane);
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
		
		this.clickButton.addActionListener(new DataListener(this.controller, this));
		this.impressionButton.addActionListener(new DataListener(this.controller, this));
		this.serverButton.addActionListener(new DataListener(this.controller, this));
		this.submitButton.addActionListener(new DataListener(this.controller, this));
		
	}
	
}

class MetricsPanel extends JPanel{
	
	private Controller controller;
	private JButton calculateButton;
	private JLabel[] impressionNumber, clickNumber, uniqueNumber, bounceNumber,
					 conversionNumber, ctr, cpa, cpc, cpm, bounceRate, totalCost;
	
	final int PER_HOUR = 0, PER_DAY = 1, IMPRESSION_COST = 0, CLICK_COST = 1;
	
	MetricsPanel(Controller controller){
		this.controller = controller;
		this.calculateButton = new JButton("Calculate Metrics");
		
		GridLayout gl = new GridLayout(0, 3);
		this.setLayout(gl);
		
		totalCost = new JLabel[2];
		totalCost[IMPRESSION_COST] = new JLabel("0");
		totalCost[CLICK_COST] = new JLabel("0");
		
		//store in 2d array
		impressionNumber = new JLabel[2];
		impressionNumber[PER_HOUR] = new JLabel("0");
		impressionNumber[PER_DAY] = new JLabel("0");		
		
		clickNumber = new JLabel[2];
		clickNumber[PER_HOUR] = new JLabel("0");
		clickNumber[PER_DAY] = new JLabel("0");	
		
		uniqueNumber = new JLabel[2];
		uniqueNumber[PER_HOUR] = new JLabel("0");
		uniqueNumber[PER_DAY] = new JLabel("0");	
		
		bounceNumber = new JLabel[2];
		bounceNumber[PER_HOUR] = new JLabel("0");
		bounceNumber[PER_DAY] = new JLabel("0");	
		
		conversionNumber = new JLabel[2];
		conversionNumber[PER_HOUR] = new JLabel("0");
		conversionNumber[PER_DAY] = new JLabel("0");	
		
		ctr = new JLabel[2];
		ctr[PER_HOUR] = new JLabel("0");
		ctr[PER_DAY] = new JLabel("0");	
		
		cpa = new JLabel[2];
		cpa[PER_HOUR] = new JLabel("0");
		cpa[PER_DAY] = new JLabel("0");	
		
		cpc = new JLabel[2];
		cpc[PER_HOUR] = new JLabel("0");
		cpc[PER_DAY] = new JLabel("0");	
		
		cpm = new JLabel[2];
		cpm[PER_HOUR] = new JLabel("0");
		cpm[PER_DAY] = new JLabel("0");	
		
		bounceRate = new JLabel[2];
		bounceRate[PER_HOUR] = new JLabel("0");
		bounceRate[PER_DAY] = new JLabel("0");	
		
		this.add(new JLabel("Metric"));
		this.add(new JLabel("Per Hour"));
		this.add(new JLabel("Per Day"));
		
		this.add(new JLabel("Number of Impressions:"));
		this.add(impressionNumber[PER_HOUR]);
		this.add(impressionNumber[PER_DAY]);
		
		this.add(new JLabel("Number of Clicks:"));
		this.add(clickNumber[PER_HOUR]);
		this.add(clickNumber[PER_DAY]);
		
		this.add(new JLabel("Number of Uniques:"));
		this.add(uniqueNumber[PER_HOUR]);
		this.add(uniqueNumber[PER_DAY]);
		
		this.add(new JLabel("Number of Bounces:"));
		this.add(bounceNumber[PER_HOUR]);
		this.add(bounceNumber[PER_DAY]);
		
		this.add(new JLabel("Number of Conv:"));
		this.add(conversionNumber[PER_HOUR]);
		this.add(conversionNumber[PER_DAY]);
		
		this.add(new JLabel("Click-through-rate:"));
		this.add(ctr[PER_HOUR]);
		this.add(ctr[PER_DAY]);
		
		this.add(new JLabel("Cost-per-aquisition:"));
		this.add(cpa[PER_HOUR]);
		this.add(cpa[PER_DAY]);
		
		this.add(new JLabel("Cost-per-click:"));
		this.add(cpc[PER_HOUR]);
		this.add(cpc[PER_DAY]);
		
		this.add(new JLabel("Cost-per-thousand-impressions:"));
		this.add(cpm[PER_HOUR]);
		this.add(cpm[PER_DAY]);
		
		this.add(new JLabel("Bounce rate:"));
		this.add(bounceRate[PER_HOUR]);
		this.add(bounceRate[PER_DAY]);
		
		this.add(new JLabel("Total Cost:"));
		this.add(totalCost[IMPRESSION_COST]);
		this.add(totalCost[CLICK_COST]);
		this.add(new JLabel(""));
		this.add(calculateButton);
		
		this.calculateButton.addActionListener(new MetricListener(this.controller, this));
	}
	
	public void displayMetrics(String[] metrics) {
		impressionNumber[PER_HOUR].setText(metrics[0]);
		impressionNumber[PER_DAY].setText(metrics[1]);
		clickNumber[PER_HOUR].setText(metrics[2]);
		clickNumber[PER_DAY].setText(metrics[3]);
		uniqueNumber[PER_HOUR].setText(metrics[4]);
		uniqueNumber[PER_DAY].setText(metrics[5]);
		bounceNumber[PER_HOUR].setText(metrics[6]);
		bounceNumber[PER_DAY].setText(metrics[7]);
		conversionNumber[PER_HOUR].setText(metrics[8]);
		conversionNumber[PER_DAY].setText(metrics[9]);
		totalCost[IMPRESSION_COST].setText(metrics[10]);
		totalCost[CLICK_COST].setText(metrics[11]);
		ctr[PER_HOUR].setText(metrics[12]);
		ctr[PER_DAY].setText(metrics[13]);
		cpa[PER_HOUR].setText(metrics[14]);
		cpa[PER_DAY].setText(metrics[15]);
		cpc[PER_HOUR].setText(metrics[16]);
		cpc[PER_DAY].setText(metrics[17]);
		cpm[PER_HOUR].setText(metrics[18]);
		cpm[PER_DAY].setText(metrics[19]);
		bounceRate[PER_HOUR].setText(metrics[20]);
		bounceRate[PER_DAY].setText(metrics[21]);
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