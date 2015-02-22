package calculator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * 
 * The input log entries must be in increasing order of date
 * 
 * The calculator considers the first entry of the log's date to be the starting date.
 * parameter intervals are in seconds.
 * 
 * For example starting from 12:00:02, interval 2
 * the first interval: [12:00:02-12:00:04) - including 12:00:02 not including 12:00:04
 * the second: [12:00:04-12:00:06)
 * @author Zd
 *
 */
public class Calculator implements CalculatorInterface
{
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
	
	private Hashtable<String, String>[] impressionLog;
	private Hashtable<String, String>[] clickLog;
	private Hashtable<String, String>[] serverLog;
	
	public Calculator(Hashtable<String, String>[] impressionLog, 
			Hashtable<String, String>[] clickLog,
			Hashtable<String, String>[] serverLog)
	{
		this.impressionLog = impressionLog;
		this.clickLog = clickLog;
		this.serverLog = serverLog;
	}
	
	/**
	 * Count the entries in the log file, arrange the count number according to interval
	 * @param interval
	 * @param log
	 * @return
	 */
	private Integer[] getCount(int interval, Hashtable<String, String>[] log)
	{
		ArrayList<Integer> dataList = new ArrayList<Integer>();
		LocalDateTime previousNode = null;
		for (Hashtable<String, String> h : impressionLog)
		{
			LocalDateTime ldt = LocalDateTime.from(fmt.parse(h.get("Date")));
			if(previousNode == null)
			{
				previousNode = ldt;
				dataList.add(1);
			}
			else
			{
				//larger or equal
				while(ldt.compareTo(previousNode.plusSeconds(interval)) >= 0)
				{
					dataList.add(0);
					previousNode = previousNode.plusSeconds(interval);	
				}
				dataList.set(dataList.size()-1, dataList.get(dataList.size()-1) + 1);
			}
		}
		return Arrays.copyOf(dataList.toArray(), dataList.toArray().length, Integer[].class);
	}
	
	@Override
	public Integer[] getImpressionNumber(int interval)
	{
		return this.getCount(interval, impressionLog);
	}

	@Override
	public Integer[] getClickNumber(int interval)
	{
		return this.getCount(interval, clickLog);
	}

	@Override
	public int[] getUniqueNumber(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getBounceNumber(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getConversionNumber(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCTR(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCPA(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCPC(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCPM(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getBounceRate(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getImpressionCost(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getClickCost(int interval)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
