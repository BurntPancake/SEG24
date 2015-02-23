package gui;

import javax.swing.*;

public class TestGUI {

	public static void main(String[] args){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				display();
			}
		});
		
	}
	
	private static void display(){
		
		JFrame frame = new JFrame();
		frame.setTitle("Ad Auction Dashboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel dataPane = new DataPanel();
		JPanel chartsPane = new ChartsPanel();
		JPanel metricsPane = new MetricsPanel();

		tabbedPane.addTab("Data", dataPane);
		tabbedPane.addTab("Charts", chartsPane);
		tabbedPane.addTab("Metrics", metricsPane);
		
		frame.setContentPane(tabbedPane);
	
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
}

class DataPanel extends JPanel{
	
	DataPanel(){
		
		this.add(new JTextArea(30, 50));
		this.add(new JButton("Open"));
		
	}
	
}

class ChartsPanel extends JPanel{
	
	ChartsPanel() {
		

		this.add(new JButton("Test Button 1"));
		this.add(new JButton("Button Two"));
		
	}
	
}

class MetricsPanel extends JPanel{
	
	MetricsPanel(){
		
		this.add(new JLabel("Metrics stuff"));
		
	}
	
}
