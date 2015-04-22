package calculator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

import decoder.Click;
import decoder.Impression;
import decoder.Server;

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
public class Calculator
{
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
							
	private ArrayList<Impression> impressionLog;
	private ArrayList<Click> clickLog;
	private ArrayList<Server> serverLog;
	
	public Calculator(ArrayList<Impression> impressionLog, 
			ArrayList<Click> clickLog,
			ArrayList<Server> serverLog)
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
	private ArrayList<Integer> getImpCount(int interval, ArrayList<Impression> log)
	{
		ArrayList<Integer> dataList = new ArrayList<Integer>(log.size());
		LocalDateTime previousNode = null;
		for (Impression h : log)
		{
			LocalDateTime ldt;
			ldt = LocalDateTime.from(fmt.parse(h.date));
			
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
					//System.out.println("Enter the new interval" + ldt.toString());
					dataList.add(0);
					previousNode = previousNode.plusSeconds(interval);	
				}
				dataList.set(dataList.size()-1, dataList.get(dataList.size()-1) + 1);
			}
		}
		//System.out.println("Progressing");
		return dataList;
	}
	
	private ArrayList<Integer> getClickCount(int interval, ArrayList<Click> log)
	{
		ArrayList<Integer> dataList = new ArrayList<Integer>(log.size());
		LocalDateTime previousNode = null;
		for (Click h : log)
		{
			LocalDateTime ldt;
			ldt = LocalDateTime.from(fmt.parse(h.date));
			
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
					//System.out.println("Enter the new interval" + ldt.toString());
					dataList.add(0);
					previousNode = previousNode.plusSeconds(interval);	
				}
				dataList.set(dataList.size()-1, dataList.get(dataList.size()-1) + 1);
			}
		}
		//System.out.println("Progressing");
		return dataList;
	}
	
	private ArrayList<Integer> getServerCount(int interval, ArrayList<Server> log)
	{
		ArrayList<Integer> dataList = new ArrayList<Integer>(log.size());
		LocalDateTime previousNode = null;
		for (Server h : log)
		{
			LocalDateTime ldt;
			ldt = LocalDateTime.from(fmt.parse(h.date));
			
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
					//System.out.println("Enter the new interval" + ldt.toString());
					dataList.add(0);
					previousNode = previousNode.plusSeconds(interval);	
				}
				dataList.set(dataList.size()-1, dataList.get(dataList.size()-1) + 1);
			}
		}
		//System.out.println("Progressing");
		return dataList;
	}
	
	private ArrayList<Float> getImpCost(int interval, ArrayList<Impression> log)
	{
		ArrayList<Float> dataList = new ArrayList<Float>(log.size());
		LocalDateTime previousNode = null;
		for (Impression h : log)
		{
			LocalDateTime ldt;
			ldt = LocalDateTime.from(fmt.parse(h.date));
			if(previousNode == null)
			{
				previousNode = ldt;
				dataList.add(Float.valueOf(h.cost));
			}
			else
			{
				//larger or equal
				while(ldt.compareTo(previousNode.plusSeconds(interval)) >= 0)
				{
					dataList.add(0f);
					previousNode = previousNode.plusSeconds(interval);	
				}
				dataList.set(dataList.size()-1, dataList.get(dataList.size()-1) + Float.valueOf(h.cost));
			}
		}
		//System.out.println("Progressing");
		return dataList;
	}
	
	private ArrayList<Float> getClickCost(int interval, ArrayList<Click> log)
	{
		ArrayList<Float> dataList = new ArrayList<Float>(log.size());
		LocalDateTime previousNode = null;
		for (Click h : log)
		{
			LocalDateTime ldt;
			ldt = LocalDateTime.from(fmt.parse(h.date));
			if(previousNode == null)
			{
				previousNode = ldt;
				dataList.add(Float.valueOf(h.cost));
			}
			else
			{
				//larger or equal
				while(ldt.compareTo(previousNode.plusSeconds(interval)) >= 0)
				{
					dataList.add(0f);
					previousNode = previousNode.plusSeconds(interval);	
				}
				dataList.set(dataList.size()-1, dataList.get(dataList.size()-1) + Float.valueOf(h.cost));
			}
		}
		//System.out.println("Progressing");
		return dataList;
	}
	
	public ArrayList<Integer> getImpressionNumber(int interval)
	{
		System.out.println("Getting impression number");
		return this.getImpCount(interval, impressionLog);
	}

	public ArrayList<Integer> getClickNumber(int interval)
	{
		System.out.println("Getting click number");
		return this.getClickCount(interval, clickLog);
	}

	public ArrayList<Integer> getUniqueNumber(int interval)
	{
		System.out.println("Getting unique number");
		int logSize = clickLog.size();
		ArrayList<Click> newTable = new ArrayList<Click>(logSize);
		HashSet<Long> userIds = new HashSet<Long>(logSize);
		for(int i = 0; i < logSize; i++)
		{
			long userId = clickLog.get(i).id;
			
			if(userIds.contains(userId))
			{
				
			}
			else
			{
				userIds.add(userId);
				newTable.add(clickLog.get(i));
			}
		}
		
		return this.getClickCount(interval, newTable);
	}

	public ArrayList<Integer> getConversionNumber(int interval)
	{
		System.out.println("Getting conversion number");
		ArrayList<Server> newTable = new ArrayList<Server>(serverLog.size());
		for(Server h : serverLog)
		{
			if(h.conversion)
			{
				newTable.add(h);
			}
		}
		
		return this.getServerCount(interval, newTable);
	}
	
	public ArrayList<Float> getImpressionCost(int interval)
	{
		System.out.println("Getting impression cost");
		return getImpCost(interval, impressionLog);
	}

	public ArrayList<Float> getClickCost(int interval)
	{
		System.out.println("Getting click cost");
		return getClickCost(interval, clickLog);
	}
	
	public ArrayList<Float> getHistogramClicks()
	{
		ArrayList<Float> temp = new ArrayList<Float>(clickLog.size());
		for (Click entry : clickLog)
		{
			temp.add(Float.valueOf(entry.cost));
		}
		ArrayList<Float> values = new ArrayList<Float>(temp.size());
		
		return values;
	}

	/**
	 * 
	 * average number of clicks per impression
	 */
	public ArrayList<Float> getCTR(int interval)
	{
		System.out.println("Getting CTR");
		ArrayList<Integer> clickArray = this.getClickNumber(interval);
		ArrayList<Integer> impressionList = this.getImpressionNumber(interval);
		ArrayList<Float> CTRArray = new ArrayList<Float>();
		for(int i = 0; i < CTRArray.size(); i++)
		{
			if(impressionList.get(i).equals(0))
			{
				CTRArray.add((float)clickArray.get(i)/1f);
			}
			else
			{
				CTRArray.add((float)clickArray.get(i)/(float)impressionList.get(i));
			}
		}
		return CTRArray;
	}

	/**
	 * average cost per conversion
	 */
	public ArrayList<Float> getCPA(int interval)
	{
		System.out.println("Getting CPA");
		ArrayList<Float> clickCostArray = this.getClickCost(interval);
		ArrayList<Integer> conversionCountArray = this.getConversionNumber(interval);
		ArrayList<Float> CPAArray = new ArrayList<Float>();
		
		System.out.println(clickCostArray.length);
		System.out.println(conversionCountArray.length);
		
		for(int i = 0; i < CPAArray.length; i++)
		{
			if(conversionCountArray[i].equals(0))
			{
				CPAArray[i] = (float)clickCostArray[i]/1f;
			}
			else
			{
				CPAArray[i] = clickCostArray[i]/(float)conversionCountArray[i];
			}
			
		}
		return CPAArray;
	}

	/**
	 * Average cost per click
	 */
	public ArrayList<Float> getCPC(int interval)
	{
		System.out.println("Getting CPC");
		ArrayList<Float> clickCostArray = this.getClickCost(interval);
		Integer[] clickCountArray = this.getClickNumber(interval);
		ArrayList<Float> CPCArray = new Float[clickCostArray.length];
		for(int i = 0; i < clickCostArray.length; i++)
		{
			if(clickCountArray[i].equals(0))
			{
				CPCArray[i] = (float)clickCostArray[i]/1f;
			}
			else
			{
				CPCArray[i] = clickCostArray[i]/(float)clickCountArray[i];
			}	
		}
		return CPCArray;
	}
	
	/**
	 * Average cost per thousand impressions
	 */
	public ArrayList<Float> getCPM(int interval)
	{
		System.out.println("Getting CPM");
		ArrayList<Float> impressionCostArray = this.getImpressionCost(interval);
		Integer[] impressionCountArray = this.getImpressionNumber(interval);
		ArrayList<Float> CPMArray = new Float[impressionCostArray.length];
		for(int i = 0; i < impressionCostArray.length; i++)
		{
			if(impressionCountArray[i].equals(0))
			{
				CPMArray[i] = impressionCostArray[i]*1000f;
			}
			else
			{
				CPMArray[i] = impressionCostArray[i]/(float)impressionCountArray[i]*1000f;
			}
			
		}
		return CPMArray;
	}

	/**
	 * if smaller or equal => count as a bounce
	 */
	public Integer[] getBounceNumberByPages(int interval, int pageViewed)
	{
		System.out.println("Getting bounce number by pages");
		ArrayList<Hashtable<String, String>> newTable = new ArrayList<Hashtable<String, String>>(serverLog.length);
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
	
	/**
	 * if smaller or equal => count as a bounce
	 */
	public Integer[] getBounceNumberByTime(int interval, int timeSpent)
	{
		System.out.println("Getting bounce number by time");
		ArrayList<Hashtable<String, String>> newTable = new ArrayList<Hashtable<String, String>>(serverLog.length);
		for(int i = 0; i < serverLog.length; i++)
		{
			if(!serverLog[i].get("Exit Date").equals("n/a"))
			{
				LocalDateTime entryDate = LocalDateTime.from(fmt.parse(serverLog[i].get("Entry Date")));
				LocalDateTime exitDate = LocalDateTime.from(fmt.parse(serverLog[i].get("Exit Date")));
				
				if(Duration.between(entryDate, exitDate).getSeconds() <= timeSpent)
				{
					newTable.add(serverLog[i]);
				}
			}	
		}

		Hashtable<String, String>[] newLog = (Hashtable<String, String>[]) new Hashtable[newTable.size()];;
		newTable.toArray(newLog);
		
		return this.getCount(interval, newLog);
	}
	

	public ArrayList<Float> getBounceRateByPages(int interval, int pageViewed)
	{
		System.out.println("Getting bounce rate by pages");
		Integer[] bounceCountArray = this.getBounceNumberByPages(interval, pageViewed);
		Integer[] clickCountArray = this.getClickNumber(interval);
		ArrayList<Float> bounceRateArray = new Float[bounceCountArray.length];
		for(int i = 0; i < bounceCountArray.length; i++)
		{
			if(clickCountArray[i].equals(0))
			{
				bounceRateArray[i] = (float)bounceCountArray[i]/1f;
			}
			else
			{
				bounceRateArray[i] = (float)bounceCountArray[i]/(float)clickCountArray[i];
			}
			
		}
		return bounceRateArray;
	}


	public ArrayList<Float> getBounceRateByTime(int interval, int timeSpent)
	{
		System.out.println("Getting bounce rate by time");
		Integer[] bounceCountArray = this.getBounceNumberByTime(interval, timeSpent);
		Integer[] clickCountArray = this.getClickNumber(interval);
		ArrayList<Float> bounceRateArray = new Float[bounceCountArray.length];
		for(int i = 0; i < bounceCountArray.length; i++)
		{
			if(clickCountArray[i].equals(0))
			{
				bounceRateArray[i] = (float)bounceCountArray[i]/1f;
			}
			else
			{
				bounceRateArray[i] = (float)bounceCountArray[i]/(float)clickCountArray[i];
			}
			
		}
		return bounceRateArray;
	}
	
	private float getLargestClickCost()
	{
		float max = 0f;
		for(Hashtable<String, String> h : clickLog)
		{
			float cost = Float.parseFloat(h.get("Click Cost"));
			if(cost > max)
			{
				max = cost;
			}
		}
		
		return max;
	}

	public Integer[] getClickCostDistribution(float costInterval)
	{
		System.out.println("Getting click cost distribution");
		Integer[] dataSet = new Integer[(int) (getLargestClickCost()/costInterval) + 1];
		for(Hashtable<String, String> h : clickLog)
		{
			float cost = Float.valueOf(h.get("Click Cost"));
			Integer currentCostInterval = (int) (cost/costInterval); //Not round! Only the integer part matters!
			if(dataSet[currentCostInterval] == null)
			{
				dataSet[currentCostInterval] = 1;
			}
			else
			{
				dataSet[currentCostInterval] = dataSet[currentCostInterval]+1;
			}
			
			for(Integer i : dataSet)
			{
				if (i == null)
				{
					i = 0;
				}
			}
		}
		
		return dataSet;
	}
	
}
