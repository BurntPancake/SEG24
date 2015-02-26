package sorter;

import java.util.Arrays;
import java.util.Hashtable;

public class RecordSorter {

	public void sortRecords(Hashtable<String, String>[] recordList, String sortingField) {
		RecordComparator comp = new RecordComparator(sortingField);
		Arrays.sort(recordList, comp);
	}
	
}
