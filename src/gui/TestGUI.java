package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private JButton open;
	JFileChooser fc;
	private Controller controller;
	
	DataPanel(Controller controller){
		
		this.controller = controller;
		this.add(new JTextArea(30, 50));
		open = new JButton("Open");
		
		open.addActionListener(new DataListener(controller, this));
		
		this.add(open);
		
	}
	
}

class ChartsPanel extends JPanel{
	
	ChartsPanel(Controller controller) {
		
		this.add(new JButton("Test Button 1"));
		this.add(new JButton("Button Two"));
		
	}
	
}

class MetricsPanel extends JPanel{
	
	MetricsPanel(Controller controller){
		
		this.add(new JLabel("Metrics stuff"));
		
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
		/*
		fc = new JFileChooser();
		
		int returnVal = fc.showOpenDialog(dp);
		
		if (returnVal == JFileChooser.APPROVE_OPTION){
			String filePath = fc.getSelectedFile().getAbsolutePath();
			controller.setFileLocation(filePath);
		}
		*/
	}
	
}