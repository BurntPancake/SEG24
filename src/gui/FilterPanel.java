package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

class FilterPanel extends JPanel {
	
	private Controller controller;
	
	public FilterPanel(Controller controller) {
		this.controller = controller;
		init();
	}
	
	public void init() {
		String[] contexts = { "News", "Shopping", "Social Media", "Blog", "Hobbies", "Travel" };
		String[] ages = { "Under 25", "25 to 34", "35 to 44", "45 to 54", "Over 54" };
		String[] incomes = { "No preference", "High", "Medium", "Low" };
		
		JLabel contextLabel = new JLabel("Context:");
		JList contextList = new JList(contexts);
		contextList.setSelectionModel(new MyListSelectionModel());
		
		JLabel ageLabel = new JLabel("Age Range:");
		JList ageRange = new JList(ages);
		ageRange.setSelectionModel(new MyListSelectionModel());		
		
		JLabel incomeLabel = new JLabel("Income:");
		JCheckBox highBox = new JCheckBox("High");
		JCheckBox mediumBox = new JCheckBox("Medium");
		JCheckBox lowBox = new JCheckBox("Low");
		
		JCheckBox maleBox = new JCheckBox("Male");
		JCheckBox femaleBox = new JCheckBox("Female");
		
		JLabel startLabel = new JLabel("Start date:");
		JLabel endLabel = new JLabel("End date:");
		JTextField startDate = new JTextField();
		JTextField endDate = new JTextField();
		
		JButton resetButton = new JButton("Reset");
		JButton applyButton = new JButton("Apply");
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.insets = new Insets(0, 0, 0, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		this.add(contextLabel, cons);
		
		cons.gridy = 1;
		cons.gridheight = 3;
		this.add(contextList, cons);
		
		cons.gridy = 0;
		cons.gridx = 2;
		cons.gridheight = 1;
		cons.insets = new Insets(0, 5, 0, 0);
		this.add(ageLabel, cons);
		
		cons.gridy = 1;
		cons.gridheight = 3;
		this.add(ageRange, cons);
		
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridwidth = 3;
		cons.gridheight = 1;
		cons.insets = new Insets(0, 0, 0, 0);
		this.add(incomeLabel, cons);
		
		cons.gridy = 5;
		cons.gridwidth = 1;
		this.add(highBox, cons);
		
		cons.gridx = 1;
		this.add(mediumBox, cons);
		
		cons.gridx = 2;
		this.add(lowBox, cons);
		
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridwidth = 3;
		this.add(new JLabel("Gender:"), cons);
		
		cons.gridy = 7;
		cons.gridwidth = 1;
		this.add(maleBox, cons);
		
		cons.gridx = 2;
		this.add(femaleBox, cons);
		
		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridwidth = 3;
		this.add(startLabel, cons);
		
		cons.gridy = 9;
		this.add(startDate, cons);
		
		cons.gridy = 10;
		this.add(endLabel, cons);
		
		cons.gridy = 11;
		this.add(endDate, cons);
		
		cons.gridy = 12;
		cons.gridwidth = 1;
		this.add(resetButton, cons);
		
		cons.gridx = 2;
		this.add(applyButton, cons);
		
	}
	
}

class MyListSelectionModel extends DefaultListSelectionModel {
    @Override
    public void setSelectionInterval(int index0, int index1) {
        if(super.isSelectedIndex(index0)) {
            super.removeSelectionInterval(index0, index1);
        }
        else {
            super.addSelectionInterval(index0, index1);
        }
    }
}