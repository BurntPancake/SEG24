package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

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
	private MetricsPanel metricsPanel;
	
	DataPanel(Controller controller, MetricsPanel mp){
		
		this.controller = controller;
		this.metricsPanel = mp;
		
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
		
		DataListener dl = new DataListener(this.controller, this, mp);
		this.clickButton.addActionListener(new DataListener(this.controller, this, mp));
		this.impressionButton.addActionListener(new DataListener(this.controller, this, mp));
		this.serverButton.addActionListener(new DataListener(this.controller, this, mp));
		this.submitButton.addActionListener(new DataListener(this.controller, this, mp));
		
	}
	
}
