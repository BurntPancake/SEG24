package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import org.junit.Test;

import calculator.Calculator;
import decoder.Decoder;
import decoder.DecoderInterface;
import filter.Filter;

public class FilterTest
{

	@Test
	public void test()
	{
		DecoderInterface dec = new Decoder();
		try
		{
			Hashtable<String, String>[] impressionData = dec.getData("ExampleInputData/impression_log_test.csv");
			Hashtable<String, String>[] clickData = dec.getData("ExampleInputData/click_log_test.csv");
			Hashtable<String, String>[] serverData = dec.getData("ExampleInputData/server_log_test.csv");
			Calculator calc = new Calculator(impressionData, clickData, serverData);
			Filter filter = new Filter();
			
			//Test filter table by time range
			Hashtable<String, String>[] newLog = filter.filterTableByTimeInterval(impressionData, "2015-01-01 12:00:04", "2015-01-01 12:00:13");
			assertEquals(7, newLog.length);
			
			//test filter table by specific filed
			ArrayList<String> filterOption = new ArrayList<String>();
			filterOption.add("High");
			filterOption.add("Medium");
			newLog = filter.filterTablebyField(impressionData, "Income", filterOption);
			assertEquals(6, newLog.length);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}