package gui;

import java.awt.*;

import javax.swing.*;

import controller.Controller;

public class GUI {

	private Controller controller;
	ChartsPanel chartPanel;
	JTabbedPane tabbedPane;
	JFrame frame;
	JPanel chartsPane;
	
	public GUI(Controller controller){
		this.controller = controller;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				display();
			}
		});
	}
		
	private void display(){
		
		frame = new JFrame();
		frame.setTitle("Ad Auction Dashboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		tabbedPane = new JTabbedPane();
		
		chartsPane = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 0.6;
		chartPanel = new ChartsPanel(this.controller);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartsPane.add(new ChartsPanel(this.controller), cons);
		
		cons.gridx = 1;
		cons.weightx = 0.4;
		FilterPanel filterPane = new FilterPanel(this.controller, this.chartPanel, this);
		chartsPane.add(filterPane, cons);
	
		MetricsPanel mp = new MetricsPanel(this.controller);
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 2;
		chartsPane.add(mp, cons);
		
		JPanel dataPane = new DataPanel(this.controller, mp, frame, filterPane, this);
		
		tabbedPane.addTab("Data", dataPane);
		tabbedPane.addTab("Charts", chartsPane);
		
		frame.setContentPane(tabbedPane);
		frame.setResizable(true);
	
		frame.pack();
		frame.setVisible(true);		
	}
	
	/*
	 * Wanted to make automatically change to Charts Tab upon submission completion
	 * but not implemented
	 * 
	*/
	void changePane(int indexToView, JTabbedPane tabbedPane){
		
		tabbedPane.setSelectedIndex(indexToView);
		
	}
	/*
	void updateFrame(JFrame frame){
		chartsPane.revalidate();
		chartsPane.repaint();
	}
	*/
	
}
