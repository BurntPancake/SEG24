package gui;

import javax.swing.table.DefaultTableModel;

public class MetricTableModel extends DefaultTableModel {

	public MetricTableModel(Object[][] tableData, Object[] columnNames) {
		super(tableData, columnNames);
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
