package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import controller.Controller;

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

		tabbedPane.addTab("Data", dataPane);
		tabbedPane.addTab("Charts", chartsPane);
		tabbedPane.addTab("Metrics", metricsPane);
		
		frame.setContentPane(tabbedPane);
	
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

class ChartsPanel extends JPanel{
	
	ChartsPanel(Controller controller) {
		
		this.add(new JButton("Test Button 1"));
		this.add(new JButton("Button Two"));
		
	}
	
}

class MetricsPanel extends JPanel{
	
	private Controller controller;
	private JButton calculateButton;
	private JLabel[] impressionNumber, clickNumber, uniqueNumber, bounceNumber,
					 conversionNumber, ctr, cpa, cpc, cpm, bounceRate;
	private JLabel totalCost;
	
	final int PER_MINUTE = 0, PER_HOUR = 1, PER_DAY = 2;
	
	MetricsPanel(Controller controller){
		this.controller = controller;
		this.calculateButton = new JButton("Calculate Metrics");
		
		GridLayout gl = new GridLayout(12, 4);
		this.setLayout(gl);
		totalCost = new JLabel("0");
		
		impressionNumber = new JLabel[3];
		impressionNumber[PER_MINUTE] = new JLabel("0");
		impressionNumber[PER_HOUR] = new JLabel("0");
		impressionNumber[PER_DAY] = new JLabel("0");		
		
		clickNumber = new JLabel[3];
		clickNumber[PER_MINUTE] = new JLabel("0");
		clickNumber[PER_HOUR] = new JLabel("0");
		clickNumber[PER_DAY] = new JLabel("0");	
		
		uniqueNumber = new JLabel[3];
		uniqueNumber[PER_MINUTE] = new JLabel("0");
		uniqueNumber[PER_HOUR] = new JLabel("0");
		uniqueNumber[PER_DAY] = new JLabel("0");	
		
		bounceNumber = new JLabel[3];
		bounceNumber[PER_MINUTE] = new JLabel("0");
		bounceNumber[PER_HOUR] = new JLabel("0");
		bounceNumber[PER_DAY] = new JLabel("0");	
		
		conversionNumber = new JLabel[3];
		conversionNumber[PER_MINUTE] = new JLabel("0");
		conversionNumber[PER_HOUR] = new JLabel("0");
		conversionNumber[PER_DAY] = new JLabel("0");	
		
		ctr = new JLabel[3];
		ctr[PER_MINUTE] = new JLabel("0");
		ctr[PER_HOUR] = new JLabel("0");
		ctr[PER_DAY] = new JLabel("0");	
		
		cpa = new JLabel[3];
		cpa[PER_MINUTE] = new JLabel("0");
		cpa[PER_HOUR] = new JLabel("0");
		cpa[PER_DAY] = new JLabel("0");	
		
		cpc = new JLabel[3];
		cpc[PER_MINUTE] = new JLabel("0");
		cpc[PER_HOUR] = new JLabel("0");
		cpc[PER_DAY] = new JLabel("0");	
		
		cpm = new JLabel[3];
		cpm[PER_MINUTE] = new JLabel("0");
		cpm[PER_HOUR] = new JLabel("0");
		cpm[PER_DAY] = new JLabel("0");	
		
		bounceRate = new JLabel[3];
		bounceRate[PER_MINUTE] = new JLabel("0");
		bounceRate[PER_HOUR] = new JLabel("0");
		bounceRate[PER_DAY] = new JLabel("0");	
		
		this.add(new JLabel("Metric"));
		this.add(new JLabel("Per Minute"));
		this.add(new JLabel("Per Hour"));
		this.add(new JLabel("Per Day"));
		
		this.add(new JLabel("Number of Impressions:"));
		this.add(impressionNumber[PER_MINUTE]);
		this.add(impressionNumber[PER_HOUR]);
		this.add(impressionNumber[PER_DAY]);
		
		this.add(new JLabel("Number of Clicks:"));
		this.add(clickNumber[PER_MINUTE]);
		this.add(clickNumber[PER_HOUR]);
		this.add(clickNumber[PER_DAY]);
		
		this.add(new JLabel("Number of Uniques:"));
		this.add(uniqueNumber[PER_MINUTE]);
		this.add(uniqueNumber[PER_HOUR]);
		this.add(uniqueNumber[PER_DAY]);
		
		this.add(new JLabel("Number of Bounces:"));
		this.add(bounceNumber[PER_MINUTE]);
		this.add(bounceNumber[PER_HOUR]);
		this.add(bounceNumber[PER_DAY]);
		
		this.add(new JLabel("Number of Conv:"));
		this.add(conversionNumber[PER_MINUTE]);
		this.add(conversionNumber[PER_HOUR]);
		this.add(conversionNumber[PER_DAY]);
		
		this.add(new JLabel("Click-through-rate:"));
		this.add(ctr[PER_MINUTE]);
		this.add(ctr[PER_HOUR]);
		this.add(ctr[PER_DAY]);
		
		this.add(new JLabel("Cost-per-aquisition:"));
		this.add(cpa[PER_MINUTE]);
		this.add(cpa[PER_HOUR]);
		this.add(cpa[PER_DAY]);
		
		this.add(new JLabel("Cost-per-click:"));
		this.add(cpc[PER_MINUTE]);
		this.add(cpc[PER_HOUR]);
		this.add(cpc[PER_DAY]);
		
		this.add(new JLabel("Cost-per-thousand-impressions:"));
		this.add(cpm[PER_MINUTE]);
		this.add(cpm[PER_HOUR]);
		this.add(cpm[PER_DAY]);
		
		this.add(new JLabel("Bounce rate:"));
		this.add(bounceNumber[PER_MINUTE]);
		this.add(bounceNumber[PER_HOUR]);
		this.add(bounceNumber[PER_DAY]);
		
		this.add(new JLabel("Total Cost:"));
		this.add(totalCost);
		this.add(new JLabel(""));
		this.add(calculateButton);
		
		this.calculateButton.addActionListener(new MetricListener(this.controller, this));
	}
	
	public void displayMetrics(String[] metrics) {
		
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] metrics = controller.calculateMetrics();
		mp.displayMetrics(metrics);
	}
	
}