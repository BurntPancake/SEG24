package sorter;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Test;

import decoder.Decoder;

public class RecordSorterTest {

	@Test
	public void test() {
		Decoder dec = new Decoder();
		RecordSorter rs = new RecordSorter();
		try {
			ArrayList<Hashtable<String, String>> records = dec.getData("/home/edward/university/year2/comp2211/SEG24/ExampleInputData/impression_log.csv");
			records = 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
