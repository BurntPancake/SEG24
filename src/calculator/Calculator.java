package calculator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * 
 * The input log entries must be in increasing order of date.
 * All three log files should be of one campaign.
 * 
 * The calculator considers the first entry of the log's date to be the starting date.
 * parameter intervals are in seconds.
 * 
 * For example starting from 12:00:02, interval 2
 * the first interval: [12:00:02-12:00:04) - including 12:00:02 excluding 12:00:04
 * the second interval: [12:00:04-12:00:06)
 * the third interval: [12:00:06-12:00:08)
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
		for (Hashtable<String, String> h : log)
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
					System.out.println("Enter the new interval" + ldt.toString());
					dataList.add(0);
					previousNode = previousNode.plusSeconds(interval);	
				}
				dataList.set(dataList.size()-1, dataList.get(dataList.size()-1) + 1);
			}
		}
		return Arrays.copyOf(dataList.toArray(), dataList.toArray().length, Integer[].class);
	}
	
	private Float[] getCost(int interval, Hashtable<String, String>[] log, String field)
	{
		ArrayList<Float> dataList = new ArrayList<Float>();
		LocalDateTime previousNode = null;
		for (Hashtable<String, String> h : log)
		{
			LocalDateTime ldt = LocalDateTime.from(fmt.parse(h.get("Date")));
			if(previousNode == null)
			{
				previousNode = ldt;
				dataList.add(Float.valueOf(h.get(field)));
			}
			else
			{
				//larger or equal
				while(ldt.compareTo(previousNode.plusSeconds(interval)) >= 0)
				{
					dataList.add(0f);
					previousNode = previousNode.plusSeconds(interval);	
				}
				dataList.set(dataList.size()-1, dataList.get(dataList.size()-1) + Float.valueOf(h.get(field)));
			}
		}
		return Arrays.copyOf(dataList.toArray(), dataList.toArray().length, Float[].class);
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
	public Integer[] getUniqueNumber(int interval)
	{
		ArrayList<Hashtable<String, String>> newTable = new ArrayList<Hashtable<String, String>>();
		for(int i = 0; i < clickLog.length; i++)
		{
			boolean isUnique = true;
			int j = i-1;
			while(j >= 0)
			{
				if(clickLog[j].get("ID") == clickLog[i].get("ID"))
				{
					isUnique = false;
					break;
				}
				j = j - 1;
			}
			if(isUnique)
			{
				newTable.add(clickLog[i]);
			}
		}
		
		Hashtable<String, String>[] newLog = (Hashtable<String, String>[]) new Hashtable[newTable.size()];;
		newTable.toArray(newLog);
		
		return this.getCount(interval, newLog);
	}

	@Override
	public Integer[] getConversionNumber(int interval)
	{
		ArrayList<Hashtable<String, String>> newTable = new ArrayList<Hashtable<String, String>>();
		for(Hashtable<String, String> h : serverLog)
		{
			if(h.get("Conversion").equals("Yes"))
			{
				newTable.add(h);
			}
		}
		
		Hashtable<String, String>[] newLog = (Hashtable<String, String>[]) new Hashtable[newTable.size()];;
		newTable.toArray(newLog);
		
		return this.getCount(interval, newLog);
	}
	
	@Override
	public Float[] getImpressionCost(int interval)
	{
		return getCost(interval, impressionLog, "Impression Cost");
	}

	@Override
	public Float[] getClickCost(int interval)
	{
		return getCost(interval, clickLog, "Click Cost");
	}

	@Override
	/**
	 * 
	 * average number of clicks per impression
	 */
	public Float[] getCTR(int interval)
	{
		Integer[] clickArray = this.getClickNumber(interval);
		Integer[] impressionArray = this.getImpressionNumber(interval);
		Float[] CTRArray = new Float[clickArray.length];
		for(int i = 0; i < clickArray.length; i++)
		{
			CTRArray[i] = (float)clickArray[i]/(float)impressionArray[i];
		}
		return CTRArray;
	}

	@Override
	/**
	 * average cost per conversion
	 */
	public Float[] getCPA(int interval)
	{
		Float[] clickCostArray = this.getClickCost(interval);
		Integer[] conversionCountArray = this.getConversionNumber(interval);
		Float[] CPAArray = new Float[clickCostArray.length];
		for(int i = 0; i < clickCostArray.length; i++)
		{
			CPAArray[i] = clickCostArray[i]/(float)conversionCountArray[i];
		}
		return CPAArray;
	}

	@Override
	/**
	 * Average cost per click
	 */
	public Float[] getCPC(int interval)
	{
		Float[] clickCostArray = this.getClickCost(interval);
		Integer[] clickCountArray = this.getClickNumber(interval);
		Float[] CPCArray = new Float[clickCostArray.length];
		for(int i = 0; i < clickCostArray.length; i++)
		{
			CPCArray[i] = clickCostArray[i]/(float)clickCountArray[i];
		}
		return CPCArray;
	}
	
	/**
	 * Average cost per thousand impressions
	 */
	@Override
	public Float[] getCPM(int interval)
	{
		Float[] impressionCostArray = this.getImpressionCost(interval);
		Integer[] impressionCountArray = this.getImpressionNumber(interval);
		Float[] CPMArray = new Float[impressionCostArray.length];
		for(int i = 0; i < impressionCostArray.length; i++)
		{
			CPMArray[i] = impressionCostArray[i]/(float)impressionCountArray[i]/1000f;
		}
		return CPMArray;
	}

	@Override
	/**
	 * if smaller or equal => count as a bounce
	 */
	public Integer[] getBounceNumberByPages(int interval, int pageViewed)
	{
		ArrayList<Hashtable<String, String>> newTable = new ArrayList<Hashtable<String, String>>();
		for(int i = 0; i < serverLog.length; i++)
		{
			if(Integer.parseInt(serverLog[i].get("Pages Viewed")) <= pageViewed)
			{
				newTable.add(serverLog[i]);
			}
		}

		Hashtable<String, String>[] newLog = (Hashtable<String, String>[]) new Hashtable[newTable.size()];;
		newTable.toArray(newLog);
		
		return this.getCount(interval, newLog);
	}
	
	@Override
	/**
	 * if smaller or equal => count as a bounce
	 */
	public Integer[] getBounceNumberByTime(int interval, int timeSpent)
	{
		ArrayList<Hashtable<String, String>> newTable = new ArrayList<Hashtable<String, String>>();
		for(int i = 0; i < serverLog.length; i++)
		{
			LocalDateTime entryDate = LocalDateTime.from(fmt.parse(serverLog[i].get("Entry Date")));
			LocalDateTime exitDate = LocalDateTime.from(fmt.parse(serverLog[i].get("Exit Date")));
			if(Duration.between(entryDate, exitDate).getSeconds() <= timeSpent)
			{
				newTable.add(serverLog[i]);
			}
		}

		Hashtable<String, String>[] newLog = (Hashtable<String, String>[]) new Hashtable[newTable.size()];;
		newTable.toArray(newLog);
		
		return this.getCount(interval, newLog);
	}
	
	@Override
	public Float[] getBounceRateByPages(int interval, int pageViewed)
	{
		Integer[] bounceCountArray = this.getBounceNumberByPages(interval, pageViewed);
		Integer[] clickCountArray = this.getClickNumber(interval);
		Float[] bounceRateArray = new Float[bounceCountArray.length];
		for(int i = 0; i < bounceCountArray.length; i++)
		{
			bounceRateArray[i] = (float)bounceCountArray[i]/(float)clickCountArray[i];
		}
		return bounceRateArray;
	}

	@Override
	public Float[] getBounceRateByTime(int interval, int timeSpent)
	{
		Integer[] bounceCountArray = this.getBounceNumberByTime(interval, timeSpent);
		Integer[] clickCountArray = this.getClickNumber(interval);
		Float[] bounceRateArray = new Float[bounceCountArray.length];
		for(int i = 0; i < bounceCountArray.length; i++)
		{
			bounceRateArray[i] = (float)bounceCountArray[i]/(float)clickCountArray[i];
		}
		return bounceRateArray;
	}
	
}
