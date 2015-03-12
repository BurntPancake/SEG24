package calculator;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Hashtable;

import decoder.Decoder;

public class CalculatorTest
{
	public static void main(String[] args)
	{
		/*
		//Test date format
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
		TemporalAccessor lt = fmt.parse("2015-01-01 12:00:02");
		LocalDateTime date1 = LocalDateTime.from(lt);
		LocalDateTime date2 = LocalDateTime.from(fmt.parse("2015-01-02 12:00:02"));
		System.out.println(Duration.between(date1, date2).getSeconds());
		//Test calculator functions
		Hashtable<String, String> entry0 = new Hashtable<String, String>();
		entry0.put("Date", "2015-01-01 12:00:02");
		entry0.put("Impression Cost", "0.001713");
		Hashtable<String, String> entry1 = new Hashtable<String, String>();
		entry1.put("Date", "2015-01-01 12:00:04");
		entry1.put("Impression Cost", "0.002762");
		Hashtable<String, String> entry2 = new Hashtable<String, String>();
		entry2.put("Date", "2015-01-01 12:00:05");
		entry2.put("Impression Cost", "0.001632");
		Hashtable<String, String> entry3 = new Hashtable<String, String>();
		entry3.put("Date", "2015-01-01 12:00:06");
		entry3.put("Impression Cost", "0.000000");
		Hashtable<String, String> entry4 = new Hashtable<String, String>();
		entry4.put("Date", "2015-01-01 12:00:10");
		entry4.put("Impression Cost", "0.002064");
		*/
		Decoder dec = new Decoder();
		Hashtable<String, String>[] impressionLog;
		Hashtable<String, String>[] clickLog;
		Hashtable<String, String>[] serverLog;
		try
		{
			impressionLog = dec.getData("ExampleInputData/impression_Log_test.csv");
			clickLog = dec.getData("ExampleInputData/click_log_test.csv");
			serverLog = dec.getData("ExampleInputData/server_Log_test.csv");
			Calculator cal = new Calculator(impressionLog, clickLog, serverLog);
			System.out.println("Finish readings");
			//Integer[] dataArray = cal.getClickNumber(86400);
			
			Integer[] dataArray = cal.getClickCostDistribution(10f);
			
			for (int i : dataArray)
			{
				System.out.println(i);
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		Hashtable<String, String>[] impressionLog = new Hashtable[]{
				entry0, entry1, entry2, entry3, entry4
		};
		Hashtable<String, String>[] clickLog = new Hashtable[5];
		Hashtable<String, String>[] serverLog = new Hashtable[5];
		*/
		
		

		
	}
}
