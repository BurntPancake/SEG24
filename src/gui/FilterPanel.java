package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

class FilterPanel extends JPanel 
{
	
	private Controller controller;
	
	private String[] contexts = { "News", "Shopping", "Social Media", "Blog", "Hobbies", "Travel" };
	private String[] ages = { "<25", "25-34", "35-44", "45-54", ">54" };
	//private String[] incomes = { "No preference", "High", "Medium", "Low" };
	JList<String> contextList;
	JList<String> ageRange;
	
	JCheckBox highBox;
	JCheckBox mediumBox;
	JCheckBox lowBox;
	
	JCheckBox maleBox;
	JCheckBox femaleBox;
	
	JTextField startDate;
	JTextField endDate;
	
	public FilterPanel(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void selectAllFileds(JList jlist)
	{
		for(int i = 0; i < jlist.getModel().getSize(); i++)
		{
			if(!jlist.isSelectedIndex(i))
			{
				jlist.setSelectedIndex(i);
			}
		}
	}
	
	private void resetPanel()
	{
		selectAllFileds(contextList);
		selectAllFileds(ageRange);
		
		highBox.setSelected(true);
		mediumBox.setSelected(true);
		lowBox.setSelected(true);
		
		maleBox.setSelected(true);
		femaleBox.setSelected(true);
		
		startDate.setText("2015-01-01 12:00:00");
		endDate.setText("2015-01-14 12:00:00");
	}
	
	public void init() 
	{
		JLabel contextLabel = new JLabel("Context:");
		contextList = new JList<String>(contexts);
		contextList.setSelectionModel(new MyListSelectionModel());
		
		
		JLabel ageLabel = new JLabel("Age Range:");
		ageRange = new JList<String>(ages);
		ageRange.setSelectionModel(new MyListSelectionModel());	
		
		
		JLabel incomeLabel = new JLabel("Income:");
		highBox = new JCheckBox("High");
		mediumBox = new JCheckBox("Medium");
		lowBox = new JCheckBox("Low");
		
		
		maleBox = new JCheckBox("Male");
		femaleBox = new JCheckBox("Female");
		
		JLabel startLabel = new JLabel("Start date:");
		JLabel endLabel = new JLabel("End date:");
		startDate = new JTextField();
		endDate = new JTextField();
		
		resetPanel();
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{resetPanel();}});
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ApplyListener());
		
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
	
	class ApplyListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//Context
			int[] selects = contextList.getSelectedIndices();
			ArrayList<String> contextSelections = new ArrayList<String>(6);
			for(int i = 0; i < selects.length; i++)
			{
				contextSelections.add((String) contextList.getModel().getElementAt(selects[i]));	
				//System.out.println((String) contextList.getModel().getElementAt(selects[i]));
			}
			controller.setContext(contextSelections);
			
			//Age range
			selects = ageRange.getSelectedIndices();
			ArrayList<String> ageSelections = new ArrayList<String>(5);
			for(int i = 0; i < selects.length; i++)
			{
				ageSelections.add((String) ageRange.getModel().getElementAt(selects[i]));	
			}
			controller.setAgeRange(ageSelections);
			
			//Income
			ArrayList<String> incomeSelections = new ArrayList<String>(3);
			if(highBox.isSelected())
			{
				incomeSelections.add("High");
			}
			
			if(mediumBox.isSelected())
			{
				incomeSelections.add("Medium");
			}
			
			if(lowBox.isSelected())
			{
				incomeSelections.add("Low");
			}
			controller.setIncomeRange(incomeSelections);
			
			//Gender
			ArrayList<String> genderSelections = new ArrayList<String>(2);
			if(maleBox.isSelected())
			{
				genderSelections.add("Male");
			}
			
			if(femaleBox.isSelected())
			{
				genderSelections.add("Female");
			}
			controller.setGender(genderSelections);
			
			//Date
			controller.SetDateRange(startDate.getText(), endDate.getText());
		}
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









