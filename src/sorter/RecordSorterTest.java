package sorter;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Hashtable;

import org.junit.Test;

import decoder.Decoder;

public class RecordSorterTest {

	@Test
	public void test() {
		Decoder dec = new Decoder();
		RecordSorter rs = new RecordSorter();
		try {
			Hashtable<String, String>[] records = dec.getData("/home/ej1g13/Documents/university/SEG24/ExampleInputData/server_log.csv");
			records = rs.sortRecords(records, "Entry Date");
			assertTrue(checkOrdering(records, "Entry Date"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkOrdering(Hashtable<String, String>[] records, String sortingField) {
		
		int entries = records.length;
		
		for (int i = 0; i < entries - 1; ++i) {
			Hashtable<String, String> fst = records[i];
			Hashtable<String, String> snd = records[i+1];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			try {
				Date fstDate = sdf.parse(fst.get(sortingField));
				Date sndDate = sdf.parse(snd.get(sortingField));
				if (fstDate.compareTo(sndDate) > 0) 
					return false;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return true;
		
	}

}
