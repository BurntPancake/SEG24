package sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class RecordSorter {

	public ArrayList<Hashtable<String, String>> sortRecords(ArrayList<Hashtable<String, String>> recordList, String sortingField) {
		RecordComparator comp = new RecordComparator(sortingField);
		Collections.sort(recordList, comp);
		return recordList;
	}
	
}
