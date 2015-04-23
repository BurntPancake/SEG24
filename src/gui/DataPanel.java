package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.Controller;

public class DataPanel extends JPanel{
	
	JButton submitButton;
	JButton clickButton;
	JButton impressionButton;
	JButton serverButton;
	JTextField clickField;
	JTextField impressionField;
	JTextField serverField;
	JTextField errorField;
	JLabel blankLabel, loadingField, loadingLabel;
	private JFileChooser fc;
	private GridBagConstraints gbc;
	private Controller controller;
	private MetricsPanel metricsPanel;
	
	public DataPanel(Controller controller, MetricsPanel mp, JFrame frame){
		
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
		
		this.errorField.setVisible(false);
		
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
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;

		blankLabel = new JLabel("");
		ImageIcon loadingGif = new ImageIcon(this.getClass().getResource("/images/ajax-loader.gif"));
		loadingLabel = new JLabel("Loading...", loadingGif, JLabel.CENTER);
		this.add(loadingLabel, gbc);
		loadingLabel.setVisible(false);
		
		FileFinder ff = new FileFinder();
		ff.searchDirectory(fc.getCurrentDirectory(), "impression_log.csv", "click_log.csv", "server_log.csv");
		
		for (String matched : ff.foundFiles){
			System.out.println("Found: " + matched);
		
			if (matched.endsWith("impression_log.csv")){
				this.impressionField.setText(matched);
			} else if (matched.endsWith("click_log.csv")){
				this.clickField.setText(matched);
			} else if (matched.endsWith("server_log.csv")){
				this.serverField.setText(matched);
			}
		}		
		
		DataListener dl = new DataListener(this.controller, this, mp);
		this.clickButton.addActionListener(dl);
		this.impressionButton.addActionListener(dl);
		this.serverButton.addActionListener(dl);
		this.submitButton.addActionListener(dl);
		
	}
	
	public String getClickField(){
		return this.clickField.getText();
	}
	
	public String getImpressionField(){
		return this.impressionField.getText();
	}
	
	public String getServerField(){
		return this.serverField.getText();
	}
}

class DataListener implements ActionListener {

	private Controller controller;
	private JFileChooser fc;
	private DataPanel dp;
	private MetricsPanel mp;
	
	public DataListener(Controller controller, DataPanel dp, MetricsPanel mp) {
		this.controller = controller;
		this.dp = dp;
		this.mp = mp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		dp.loadingLabel.setVisible(true);;
		new Thread(new Runnable(){

		    @Override
		    public void run(){
		       
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
						dp.errorField.setVisible(true);
					} else{
						dp.errorField.setText("");
						dp.errorField.setVisible(false);
						controller.setFileLocation(dp.impressionField.getText(), dp.clickField.getText(), dp.serverField.getText());
						mp.displayMetrics(controller.calculateMetrics());
					}
				} 
		    	
		    	boolean done = true;
		    	
		    	if(done){
		    		SwingUtilities.invokeLater(new Runnable(){
		    			@Override public void run(){
		                dp.loadingLabel.setVisible(false);      
		    			}
		    		});
		    	}
		    	
		    	dp.errorField.setText("Success! Change to Charts Tab");
		    	dp.errorField.setVisible(true);
		    	dp.revalidate();
		    	dp.repaint();
		    	
		    }

		}).start();
		
	}
}
