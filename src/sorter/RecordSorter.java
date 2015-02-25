package sorter;

import java.util.Arrays;
import java.util.Hashtable;

public class RecordSorter {

	public Hashtable<String, String>[] sortRecords(Hashtable<String, String>[] recordList, String sortingField) {
		RecordComparator comp = new RecordComparator(sortingField);
		Arrays.sort(recordList, comp);
		return recordList;
	}
	
}
