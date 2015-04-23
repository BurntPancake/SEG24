package filter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import decoder.Click;
import decoder.Impression;
import decoder.Server;

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
	public ArrayList<Impression> filterImpTableByTimeInterval(
			ArrayList<Impression> log, String startDate, String endDate) 
	{
		ArrayList<Impression> newTable = new ArrayList<Impression>();
		LocalDateTime start = LocalDateTime.from(fmt.parse(startDate));
		LocalDateTime end = LocalDateTime.from(fmt.parse(endDate));
		
		//System.out.println("Start Date Filtered: " + start.toString() );
		//System.out.println("End Date Filtered: " + end.toString() );
		for (Impression h : log)
		{
			LocalDateTime ldt;
			ldt = LocalDateTime.from(fmt.parse(h.date));
			
			if ((ldt.isAfter(start) || ldt.isEqual(start)) && ldt.isBefore(end))
			{
				newTable.add(h);
			}
		}
		System.out.println(newTable.size());
		
		return newTable;
	}
	
	public ArrayList<Click> filterClickTableByTimeInterval(
			ArrayList<Click> log, String startDate, String endDate) 
	{
		ArrayList<Click> newTable = new ArrayList<Click>();
		LocalDateTime start = LocalDateTime.from(fmt.parse(startDate));
		LocalDateTime end = LocalDateTime.from(fmt.parse(endDate));
		
		//System.out.println("Start Date Filtered: " + start.toString() );
		//System.out.println("End Date Filtered: " + end.toString() );
		for (Click h : log)
		{
			LocalDateTime ldt;
			ldt = LocalDateTime.from(fmt.parse(h.date));
			
			if ((ldt.isAfter(start) || ldt.isEqual(start)) && ldt.isBefore(end))
			{
				newTable.add(h);
			}
		}
		System.out.println(newTable.size());
		
		return newTable;
	}
	
	public ArrayList<Server> filterServerTableByTimeInterval(
			ArrayList<Server> log, String startDate, String endDate) 
	{
		ArrayList<Server> newTable = new ArrayList<Server>();
		LocalDateTime start = LocalDateTime.from(fmt.parse(startDate));
		LocalDateTime end = LocalDateTime.from(fmt.parse(endDate));
		
		//System.out.println("Start Date Filtered: " + start.toString() );
		//System.out.println("End Date Filtered: " + end.toString() );
		for (Server h : log)
		{
			LocalDateTime ldt;
			ldt = LocalDateTime.from(fmt.parse(h.date));
			
			if ((ldt.isAfter(start) || ldt.isEqual(start)) && ldt.isBefore(end))
			{
				newTable.add(h);
			}
		}
		System.out.println(newTable.size());
		
		return newTable;
	}
	
	public ArrayList<Click> filterClickTablebyID(ArrayList<Click> log, HashSet<Long> idList)
	{
		
		ArrayList<Click> newTable = new ArrayList<Click>();
		for (Click h : log)
		{
			if(idList.contains(h.id))
			{
				newTable.add(h);
			}
		}
		
		return newTable;
	}
	
	public ArrayList<Server> filterServerTablebyID(ArrayList<Server> log, HashSet<Long> idList)
	{
		
		ArrayList<Server> newTable = new ArrayList<Server>();
		for (Server h : log)
		{
			if(idList.contains(h.id))
			{
				newTable.add(h);
			}
		}
		
		return newTable;
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
	public ArrayList<Impression> filterTablebyField(
			ArrayList<Impression> log, String field, ArrayList<String> values)
	{
		ArrayList<Impression> newTable = new ArrayList<Impression>();
		boolean qualified = false;
		int value;
		for (Impression h : log)
		{
			for(String v : values)
			{
				value = Impression.translate(v);
				
				if(field.equals("Gender"))
				{
					if(h.gender == value)
					{
						newTable.add(h);
						break;
					}
				}
				else if(field.equals("Context"))
				{
					if(h.context == value)
					{
						newTable.add(h);
						break;
					}
				}
				else if(field.equals("Age"))
				{
					if(h.age == value)
					{
						newTable.add(h);
						break;
					}
				}
				else if(field.equals("Income"))
				{
					if(h.income == value)
					{
						newTable.add(h);
						break;
					}
				}
				
			}
		}
		
		return newTable;
	}
	

	
}














