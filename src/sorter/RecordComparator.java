package sorter;

import java.util.Comparator;
import java.util.Hashtable;

public class RecordComparator implements Comparator<Hashtable<String, String>> {

	private String sortingField;
	
	public RecordComparator(String sortingField) {
		this.sortingField = sortingField;
	}
	
	@Override
	public int compare(Hashtable<String, String> table1, Hashtable<String, String> table2) {
		
		int value1 = Integer.parseInt(table1.get(sortingField));
		int value2 = Integer.parseInt(table2.get(sortingField)); 
		
		if (value1 < value2) 
			return -1;
		else if (value1 > value2)
			return 1;
		else return 0;
		
	}
	
}
