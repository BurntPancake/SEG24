package calculator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Hashtable;

public class CalculatorTest
{
	public static void main(String[] args)
	{
		//Test date format
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
		TemporalAccessor lt = fmt.parse("2015-01-01 12:00:02");
		LocalDateTime ldt = LocalDateTime.from(lt);
		
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
		
		Hashtable<String, String>[] impressionLog = new Hashtable[]{
				entry0, entry1, entry2, entry3, entry4
		};
		Hashtable<String, String>[] clickLog = new Hashtable[5];
		Hashtable<String, String>[] serverLog = new Hashtable[5];
		
		Calculator cal = new Calculator(impressionLog, clickLog, serverLog);
		for (int i : cal.getImpressionNumber(1))
		{
			System.out.println(i);
		}
	}
}
