package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Hashtable;

import org.junit.Test;

import decoder.Decoder;
import decoder.DecoderInterface;

public class DecoderTest {

	@Test
	/* 
	 * Tests whether all the records have been read in.
	 * File paths will need to change depending on machine.
	 * Numbers attained from bash command wc -l *filename*
	 * last line of impression log is only half complete, and the decoder only reads 
	 * full logs, so the number there is one less than given by the command.
	 */
	public void allLinesReadTest() {
		DecoderInterface dec = new Decoder();
		try {
			Hashtable<String, String>[] data = dec.getData("/home/edward/university/year2/comp2211/SEG24/ExampleInputData/server_log.csv");
			assertEquals(23865, data.length);
			
			data = dec.getData("/home/edward/university/year2/comp2211/SEG24/ExampleInputData/impression_log.csv");
			assertEquals(486095, data.length);
			
			data = dec.getData("/home/edward/university/year2/comp2211/SEG24/ExampleInputData/click_log.csv");
			assertEquals(23849, data.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/*  
	 * Checks whether all hashtables in the list have the appropriate number of
	 * fields or not. 
	 */
	public void allFieldsReadTest() {
		DecoderInterface dec = new Decoder();
		int i = 0;
		try {
			Hashtable<String, String>[] data = dec.getData("/home/ej1g13/Documents/university/SEG24/ExampleInputData/server_log.csv");
			for (Hashtable<String, String> table : data)
				if (table.size() == 5) i++;
			assertEquals(23865, i);
		
			i = 0;
			data = dec.getData("/home/ej1g13/Documents/university/SEG24/ExampleInputData/impression_log.csv");
			for (Hashtable<String, String> table : data)
				if (table.size() == 7) ++i;
			assertEquals(486095, i);
		
			i = 0;
			data = dec.getData("/home/ej1g13/Documents/university/SEG24/ExampleInputData/click_log.csv");
			for (Hashtable<String, String> table : data)
				if (table.size() == 3) ++i;
			assertEquals(23849, i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Test that accesses a few randomly picked records and compares them to 
	 * what has been parsed by the decoder. Get value in list is actually 2 less
	 * than line no. in the doc due to indexing beginning at 0 in the lists and
	 * the line in the .csv has a line with the keys on.
	 */
	public void checkRandomRecords() {
		DecoderInterface dec = new Decoder();
		try {
			Hashtable<String, String>[] data = dec.getData("/home/ej1g13/Documents/university/SEG24/ExampleInputData/server_log.csv");
			assertEquals("8873755804012783616", data[10318].get("ID"));
			
			data = dec.getData("/home/ej1g13/Documents/university/SEG24/ExampleInputData/impression_log.csv");
			assertEquals("Shopping", data[36471].get("Context"));
		
			data = dec.getData("/home/ej1g13/Documents/university/SEG24/ExampleInputData/click_log.csv");
			assertEquals("5.682313", data[7651].get("Click Cost"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
