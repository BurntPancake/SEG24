package decoder;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
			ArrayList<Server> serverData = dec.getServerLogData("ExampleInputData/server_log.csv");
			assertEquals(23923, serverData.size());
			
			ArrayList<Impression> impressionData = dec.getImpressionLogData("ExampleInputData/impression_log.csv");
			assertEquals(486104, impressionData.size());
			
			ArrayList<Click> clickData = dec.getClickLogData("ExampleInputData/click_log.csv");
			assertEquals(23923, clickData.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/*  
	 * Checks whether all records in the list have the appropriate number of
	 * fields or not. 
	 */
	public void allFieldsReadTest() {
		DecoderInterface dec = new Decoder();
		int i = 0;
		try {
			ArrayList<Server> serverData = dec.getServerLogData("ExampleInputData/server_log.csv");
			for (Server table : serverData)
				if ((table.conversion == false || table.conversion == true) 
						&& table.date != null
						&& table.endDate != null
						&& table.page >= 0
						&& table.id >= 0) i++;
			assertEquals(23923, i);
		
			i = 0;
			ArrayList<Impression> impressionData = dec.getImpressionLogData("ExampleInputData/impression_log.csv");
			for (Impression table : impressionData)
				if (table.date != null 
						&& table.id != null
						&& table.gender >= 0
						&& table.income >= 0
						&& table.age >= 0
						&& table.context >= 0
						&& table.cost >= -1.0) ++i;
			assertEquals(486104, i);
		
			i = 0;
			ArrayList<Click> clickData = dec.getClickLogData("/home/edward/ExampleInputData/click_log.csv");
			for (Click table : clickData)
				if (table.date != null 
						&& table.id >= 0
						&& table.cost >= -1.0) ++i;
			assertEquals(23923, i);
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
			ArrayList<Server> serverData = dec.getServerLogData("/home/edward/ExampleInputData/server_log.csv");
			assertEquals(Long.parseLong("8873755804012783616"), serverData.get(10318).id);
			
			ArrayList<Impression> impressionData = dec.getImpressionLogData("/home/edward/ExampleInputData/impression_log.csv");
			assertEquals(2, impressionData.get(36471).context);
		
			ArrayList<Click> clickData = dec.getClickLogData("/home/edward/ExampleInputData/click_log.csv");
			assertEquals(5.682313, clickData.get(7651).cost, 0.1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/*
	 * Each of these boundary tests check the last record in the log has been 
	 * decoded correctly, and then check that the correct exception is thrown
	 * should an attempt be made to access a record precisely one entry past 
	 * the last. 
	 */
	@Test
	public void testBoundaryClickRecords() {
		
		DecoderInterface dec = new Decoder();
		ArrayList<Click> data = null;
		Click testSubject = null;
		
		try {
			
			data = dec.getClickLogData("ExampleInputData/click_log.csv");
			testSubject = data.get(23922);
			
			long id = Long.parseLong("6752478673601913856");
			float cost = new Float(0.0);
			
			assertEquals("2015-01-14 11:59:55", testSubject.date);
			assertEquals(id, testSubject.id);
			assertEquals(cost, testSubject.cost, 0.1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		exception.expect(IndexOutOfBoundsException.class);
		data.get(23923);
		
	
	}
	
	@Test
	public void testBoundaryImpressionRecords() {
		
		DecoderInterface dec = new Decoder();
		ArrayList<Impression> data = null;
		Impression testSubject = null;
		
		try {
			
			data = dec.getImpressionLogData("ExampleInputData/impression_log.csv");
			testSubject = data.get(486103);
			
			Long id = Long.parseLong("923607590768080896");
			float cost = new Float(0.002907);		
			
			assertEquals("2015-01-14 12:00:00", testSubject.date);
			assertEquals(id, testSubject.id);
			assertEquals(0, testSubject.gender);
			assertEquals(0, testSubject.age);
			assertEquals(1, testSubject.income);
			assertEquals(0, testSubject.context);
			assertEquals(cost, testSubject.cost, 0.001);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		exception.expect(IndexOutOfBoundsException.class);
		data.get(486104);
		
	}

	@Test
	public void testBoundaryServerRecords() {
	
		DecoderInterface dec = new Decoder();
		ArrayList<Server> data = null;
		Server testSubject = null;
		
		try {
			
			data = dec.getServerLogData("ExampleInputData/server_log.csv");
			testSubject = data.get(23922);
			
			long id = Long.parseLong("6752478673601913856");
			
			assertEquals("2015-01-14 11:59:56", testSubject.date);
			assertEquals(id, testSubject.id);
			assertEquals("2015-01-14 12:07:53", testSubject.endDate);
			assertEquals(6, testSubject.page);
			assertEquals(false, testSubject.conversion);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		exception.expect(IndexOutOfBoundsException.class);
		data.get(23923);
		
	}
	
}
