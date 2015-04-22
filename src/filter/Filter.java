package filter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;

public class Filter
{
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
	
	public Filter()
	{
		
	}
	
	/**
	 * Use this if you need a different date format than the default one
	 * @param dateFormat
	 */
	public Filter(String dateFormat)
	{
		fmt = DateTimeFormatter.ofPattern(dateFormat);
	}
	
	/**
	 * The returned hashtable will contain
	 * entries after or equal to the start date, before or equal to the end date
	 * 
	 * Date should be in the format of the input csv files.
	 * eg. 
	 * 2015-01-01 12:01:21
	 * 2015-01-01 12:04:11
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Hashtable<String, String>[] filterTableByTimeInterval(
			Hashtable<String, String>[] log, String startDate, String endDate) 
	{
		ArrayList<Hashtable<String, String>> newTable = new ArrayList<Hashtable<String, String>>();
		LocalDateTime start = LocalDateTime.from(fmt.parse(startDate));
		LocalDateTime end = LocalDateTime.from(fmt.parse(endDate));
		
		System.out.println("Start Date Filtered: " + start.toString() );
		System.out.println("End Date Filtered: " + end.toString() );
		for (Hashtable<String, String> h : log)
		{
			LocalDateTime ldt;
			if(h.get("Date") != null)
			{
				ldt = LocalDateTime.from(fmt.parse(h.get("Date")));
			}
			else
			{
				ldt = LocalDateTime.from(fmt.parse(h.get("Entry Date")));
			}
			
			if (ldt.isAfter(start) || ldt.isEqual(start) && ldt.isBefore(end))
			{
				newTable.add(h);
			}
		}
		
		//Convert ArrayList to array
		Hashtable<String, String>[] newArray = new Hashtable[newTable.size()];
		for(int i = 0; i < newArray.length; i++)
		{
			newArray[i] = newTable.get(i);
		}
		
		return newArray;
	}
	
	/**
	 * The returned table will contain
	 * entries that have the value at that filed.
	 * 
	 * eg.
	 * impressionLog, "Income", {"Low", "Medium"} will filter all entries with low/medium income
	 * impressionLog, "Income", {"Low"} will filter all entries with low income
	 * @param log
	 * @param filed
	 * @param value
	 * @return
	 */
	public Hashtable<String, String>[] filterTablebyField(
			Hashtable<String, String>[] log, String field, ArrayList<String> values)
	{
		ArrayList<Hashtable<String, String>> newTable = new ArrayList<Hashtable<String, String>>();
		for (Hashtable<String, String> h : log)
		{
			
			for(String v : values)
			{
				//System.out.println(v + h.get(field));
				if(h.get(field).equals(v))
				{
					newTable.add(h);
					break;
				}
			}
		}
		
		//Convert ArrayList to array
		Hashtable<String, String>[] newArray = new Hashtable[newTable.size()];
		for(int i = 0; i < newArray.length; i++)
		{
			newArray[i] = newTable.get(i);
		}
		
		return newArray;
	}
	
}














