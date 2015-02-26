package sorter;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Hashtable;

public class RecordComparator implements Comparator<Hashtable<String, String>> {
	
	private String sortingField;
	
	public RecordComparator(String sortingField) {
		this.sortingField = sortingField;
	}
	
	@Override
	public int compare(Hashtable<String, String> table1, Hashtable<String, String> table2) {
		
		String stringDate1 = table1.get(sortingField);
		String stringDate2 = table2.get(sortingField); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date date1, date2;
		
		try{ 
			date1 = sdf.parse(stringDate1);
			date2 = sdf.parse(stringDate2);
			
			return date1.compareTo(date2);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
}
