package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

class FilterPanel extends JPanel 
{
	
	private Controller controller;
	private ChartsPanel chartsPanel;
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy,MM,dd,HH,mm,ss");
	
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
	
	JComboBox<String> startYear;
	JComboBox<String> startMonth;
	JComboBox<String> startDay;
	JComboBox<String> startHour;
	JComboBox<String> startMinute;
	
	JComboBox<String> endYear;
	JComboBox<String> endMonth;
	JComboBox<String> endDay;
	JComboBox<String> endHour;
	JComboBox<String> endMinute;
	
	public FilterPanel(Controller controller, ChartsPanel chartsPanel) {
		this.controller = controller;
		this.chartsPanel = chartsPanel;
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
	
	public void resetPanel()
	{
		selectAllFileds(contextList);
		selectAllFileds(ageRange);
		
		highBox.setSelected(true);
		mediumBox.setSelected(true);
		lowBox.setSelected(true);
		
		maleBox.setSelected(true);
		femaleBox.setSelected(true);
		
		LocalDateTime time = controller.getStartTime();
		String[] timeString = time.format(fmt).split(",");
		startYear.setSelectedItem(timeString[0]);
		startMonth.setSelectedItem(timeString[1]);
		startDay.setSelectedItem(timeString[2]);
		startHour.setSelectedItem(timeString[3]);
		startMinute.setSelectedItem(timeString[4]);
		
		time = controller.getEndTime();
		timeString = time.format(fmt).split(",");
		endYear.setSelectedItem(timeString[0]);
		endMonth.setSelectedItem(timeString[1]);
		endDay.setSelectedItem(timeString[2]);
		endHour.setSelectedItem(timeString[3]);
		endMinute.setSelectedItem(timeString[4]);
		
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
		
		String[] years = {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
		
		String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		
		String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
							"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		
		String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23"};
		
		String[] minutes = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
				"32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", 
				"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
		
		startYear = new JComboBox<String>(years);
		startMonth = new JComboBox<String>(months);
		startDay = new JComboBox<String>(days);
		startHour = new JComboBox<String>(hours);
		startMinute = new JComboBox<String>(minutes);
		
		endYear = new JComboBox<String>(years);
		endMonth = new JComboBox<String>(months);
		endDay = new JComboBox<String>(days);
		endHour = new JComboBox<String>(hours);
		endMinute = new JComboBox<String>(minutes);
		
		endDay.setSelectedIndex(13);
		
		startHour.setSelectedIndex(12);
		endHour.setSelectedIndex(12);
		
		//resetPanel();
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{resetPanel();
			controller.resetFilterOptions();}});
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ApplyListener(this));
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 2;
		cons.insets = new Insets(0, 0, 0, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		this.add(contextLabel, cons);
		
		cons.gridy = 1;
		cons.gridheight = 3;
		
		this.add(contextList, cons);
		
		cons.gridy = 0;
		cons.gridx = 4;
		cons.gridheight = 1;
		cons.insets = new Insets(0, 5, 0, 0);
		this.add(ageLabel, cons);
		
		cons.gridy = 1;
		cons.gridheight = 3;
		this.add(ageRange, cons);
		
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridwidth = 6;
		cons.gridheight = 1;
		cons.insets = new Insets(0, 0, 0, 0);
		this.add(incomeLabel, cons);
		
		cons.gridy = 5;
		cons.gridwidth = 2;
		this.add(highBox, cons);
		
		cons.gridx = 2;
		this.add(mediumBox, cons);
		
		cons.gridx = 4;
		this.add(lowBox, cons);
		
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridwidth = 6;
		this.add(new JLabel("Gender:"), cons);
		
		cons.gridy = 7;
		cons.gridwidth = 2;
		this.add(maleBox, cons);
		
		cons.gridx = 4;
		this.add(femaleBox, cons);
		
		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridwidth = 3;
		this.add(new JLabel("Start Date:"), cons);
		
		cons.gridx = 3;
		this.add(new JLabel("Start Time:"), cons);
		
		cons.gridx = 0;
		cons.gridy = 9;
		cons.gridwidth = 1;
		this.add(startYear, cons);
		
		cons.gridx = 1;
		this.add(startMonth, cons);
		
		cons.gridx = 2;
		cons.insets = new Insets(0,0,0,5);
		this.add(startDay, cons);
		
		cons.gridx = 3;
		cons.ipadx = 30;
		cons.insets = new Insets(0,0,0,0);
		this.add(startHour, cons);
		
		cons.gridx = 4;
		cons.ipadx = 0;
		this.add(startMinute, cons);
		
		cons.gridy = 10;
		cons.gridx = 0;
		cons.gridwidth = 3;
		this.add(endLabel, cons);
		
		cons.gridx = 3;
		this.add(new JLabel("End Time:"), cons);
		
		cons.gridy = 11;
		cons.gridx = 0;
		cons.gridwidth = 1;
		this.add(endYear, cons);
		
		cons.gridx = 1;
		this.add(endMonth, cons);
		
		cons.gridx = 2;
		cons.insets = new Insets(0,0,0,5);
		this.add(endDay, cons);
		
		cons.gridx = 3;
		cons.insets = new Insets(0,0,0,0);
		this.add(endHour, cons);
		
		cons.gridx = 4;
		this.add(endMinute, cons);
		
		cons.gridy = 12;
		cons.gridx = 0;
		this.add(resetButton, cons);
		
		cons.gridx = 4;
		this.add(applyButton, cons);	
	}
	
	class ApplyListener implements ActionListener
	{
		
		private FilterPanel fp;
		
		public ApplyListener(FilterPanel fp) {
			this.fp = fp;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("-----Apply Filter-----");
			controller.resetFilterOptions();
			//Context
			int[] contextSelects = contextList.getSelectedIndices();
			ArrayList<String> contextSelections = new ArrayList<String>(6);
			for(int i = 0; i < contextSelects.length; i++)
			{
				contextSelections.add((String) contextList.getModel().getElementAt(contextSelects[i]));	
				System.out.println((String) contextList.getModel().getElementAt(contextSelects[i]));
			}
			controller.setContext(contextSelections);
			controller.clearIDsUsingImp();
			
			//Age range
			int[] ageSelects = ageRange.getSelectedIndices();
			ageSelects = ageRange.getSelectedIndices();
			ArrayList<String> ageSelections = new ArrayList<String>(5);
			for(int i = 0; i < ageSelects.length; i++)
			{
				ageSelections.add((String) ageRange.getModel().getElementAt(ageSelects[i]));	
				System.out.println((String) ageRange.getModel().getElementAt(ageSelects[i]));
			}
			controller.setAgeRange(ageSelections);
			controller.clearIDsUsingImp();
			//Income
			ArrayList<String> incomeSelections = new ArrayList<String>(3);
			if(highBox.isSelected())
			{
				incomeSelections.add("High");
				System.out.println("high selected");
			}
			
			if(mediumBox.isSelected())
			{
				incomeSelections.add("Medium");
				System.out.println("medium selected");
			}
			
			if(lowBox.isSelected())
			{
				incomeSelections.add("Low");
				System.out.println("low selected");
			}
			controller.setIncomeRange(incomeSelections);
			controller.clearIDsUsingImp();
			//Gender
			ArrayList<String> genderSelections = new ArrayList<String>(2);
			if(maleBox.isSelected())
			{
				genderSelections.add("Male");
				System.out.println("male selected");
			}
			
			if(femaleBox.isSelected())
			{
				genderSelections.add("Female");
				System.out.println("female selected");
			}
			controller.setGender(genderSelections);
			controller.clearIDsUsingImp();
			//Date
			String chosenStart = "2015-01-01 12:00:00";
			String chosenEnd = "2015-01-14 12:00:00";
			
			String chosenYear = (String) startYear.getSelectedItem();
			String chosenMonth = (String) startMonth.getSelectedItem();
			String chosenDay = (String) startDay.getSelectedItem();
			String chosenHour = (String) startHour.getSelectedItem();
			String chosenMinute = (String) startMinute.getSelectedItem();
			
			if (validDate(Integer.valueOf(chosenYear), Integer.valueOf(chosenMonth), Integer.valueOf(chosenDay))) {
				chosenStart = chosenYear + "-" + chosenMonth + "-" + chosenDay + " " + chosenHour + ":" + chosenMinute + ":00";
			}
			
			chosenYear = (String) endYear.getSelectedItem();
			chosenMonth = (String) endMonth.getSelectedItem();
			chosenDay = (String) endDay.getSelectedItem();
			chosenHour = (String) endHour.getSelectedItem();
			chosenMinute = (String) endMinute.getSelectedItem();
			
			if (validDate(Integer.valueOf(chosenYear), Integer.valueOf(chosenMonth), Integer.valueOf(chosenDay))) {
				chosenEnd = chosenYear + "-" + chosenMonth + "-" + chosenDay + " " + chosenHour + ":" + chosenMinute + ":00";
			}

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
			LocalDateTime chosenStartRange = LocalDateTime.from(fmt.parse(chosenStart));
			LocalDateTime chosenEndRange = LocalDateTime.from(fmt.parse(chosenEnd));
			
			if (chosenStartRange.isAfter(chosenEndRange)) {
				JOptionPane.showMessageDialog(fp, "The end date is after the beginning date!");
			} else {
				controller.SetDateRange(chosenStart, chosenEnd);
			}
			System.out.println("-----Filter Applied-----");
			
			
			/*
			 * Doesn't update chartsPanel when called here, but does inside chartsPanel. Lord if I know
			 */
			chartsPanel.updateChart();
			chartsPanel.revalidate();
			chartsPanel.repaint();
			
		}
		
		private boolean validDate(int y, int m, int d) {
			
			if (m == 4 || m == 6 || m == 9 || m == 11) {
			
				if (d < 31) {
					return true;
				} else {
					return false;
				}
				
			} else if (m == 2) {
				
				if ((y % 4) == 0) {
				
					if (d < 30) {
						return true;
					} else {
						return false;
					}
					
				} else {
					
					if (d < 29) {
						return true;
					} else {
						return false;
					}
					
				}
				
			} else {
				return true;
			}
			
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