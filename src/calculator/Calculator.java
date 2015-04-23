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
	
	private ArrayList<Integer> ensureSize(ArrayList<Integer> log, int i)
	{
		while(log.size() < i)
		{
			log.add(0);
		}
		return log;
	}
	
	private ArrayList<Float> ensureSizeFloat(ArrayList<Float> log, int i)
	{
		while(log.size() < i)
		{
			log.add(0f);
		}
		return log;
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
		int cLength = clickArray.size();
		ArrayList<Integer> impressionList = this.getImpressionNumber(interval);
		int iLength = impressionList.size();
		if(cLength > iLength)
		{
			impressionList = ensureSize(impressionList, cLength);
			iLength = cLength;
		}
		else if(iLength > cLength)
		{
			clickArray = ensureSize(clickArray, iLength);
			cLength = iLength;
		}
		ArrayList<Float> CTRArray = new ArrayList<Float>(cLength);
		for(int i = 0; i < cLength; i++)
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
		int cLength = clickCostArray.size();
		int iLength = conversionCountArray.size();
		if(cLength > iLength)
		{
			conversionCountArray = ensureSize(conversionCountArray, cLength);
			iLength = cLength;
		}
		else if(iLength > cLength)
		{
			clickCostArray = ensureSizeFloat(clickCostArray, iLength);
			cLength = iLength;
		}
		ArrayList<Float> CPAArray = new ArrayList<Float>(cLength);
		
		for(int i = 0; i < cLength; i++)
		{
			if(conversionCountArray.get(i).equals(0))
			{
				CPAArray.add((float)clickCostArray.get(i)/1f);
			}
			else
			{
				CPAArray.add((float)clickCostArray.get(i)/(float)conversionCountArray.get(i));
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
		ArrayList<Integer> clickCountArray = this.getClickNumber(interval);
		ArrayList<Float> CPCArray = new ArrayList<Float>(clickCostArray.size());
		for(int i = 0; i < clickCostArray.size(); i++)
		{
			if(clickCountArray.get(i).equals(0))
			{
				CPCArray.add((float)clickCostArray.get(i)/1f);
			}
			else
			{
				CPCArray.add((float)clickCostArray.get(i)/(float)clickCountArray.get(i));
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
		ArrayList<Integer> impressionCountArray = this.getImpressionNumber(interval);
		ArrayList<Float> CPMArray = new ArrayList<Float>(impressionCostArray.size());
		for(int i = 0; i < impressionCostArray.size(); i++)
		{
			if(impressionCountArray.get(i).equals(0))
			{
				CPMArray.add(impressionCostArray.get(i)*1000f);
			}
			else
			{
				CPMArray.add(impressionCostArray.get(i)/(float)impressionCountArray.get(i)*1000f);
			}
			
		}
		return CPMArray;
	}

	/**
	 * if smaller or equal => count as a bounce
	 */
	public ArrayList<Integer> getBounceNumberByPages(int interval, int pageViewed)
	{
		System.out.println("Getting bounce number by pages");
		ArrayList<Server> newTable = new ArrayList<Server>(serverLog.size());
		for(int i = 0; i < serverLog.size(); i++)
		{
			if(serverLog.get(i).page <= pageViewed)
			{
				newTable.add(serverLog.get(i));
			}
		}
		
		return this.getServerCount(interval, newTable);
	}
	
	/**
	 * if smaller or equal => count as a bounce
	 */
	public ArrayList<Integer> getBounceNumberByTime(int interval, int timeSpent)
	{
		System.out.println("Getting bounce number by time");
		ArrayList<Server> newTable = new ArrayList<Server>(serverLog.size());
		for(int i = 0; i < serverLog.size(); i++)
		{
			if(!serverLog.get(i).endDate.equals("n/a"))
			{
				LocalDateTime entryDate = LocalDateTime.from(fmt.parse(serverLog.get(i).date));
				LocalDateTime exitDate = LocalDateTime.from(fmt.parse(serverLog.get(i).endDate));
				
				if(Duration.between(entryDate, exitDate).getSeconds() <= timeSpent)
				{
					newTable.add(serverLog.get(i));
				}
			}	
		}
		
		return this.getServerCount(interval, newTable);
	}
	

	public ArrayList<Float> getBounceRateByPages(int interval, int pageViewed)
	{
		System.out.println("Getting bounce rate by pages");
		ArrayList<Integer> bounceCountArray = this.getBounceNumberByPages(interval, pageViewed);
		ArrayList<Integer> clickCountArray = this.getClickNumber(interval);
		int cLength = bounceCountArray.size();
		int iLength = clickCountArray.size();
		if(cLength > iLength)
		{
			clickCountArray = ensureSize(clickCountArray, cLength);
			iLength = cLength;
		}
		else if(iLength > cLength)
		{
			bounceCountArray = ensureSize(bounceCountArray, iLength);
			cLength = iLength;
		}
		ArrayList<Float> bounceRateArray = new ArrayList<Float>(cLength);
		for(int i = 0; i < cLength; i++)
		{
			if(clickCountArray.get(i).equals(0))
			{
				bounceRateArray.add((float)bounceCountArray.get(i)/1f);
			}
			else
			{
				bounceRateArray.add((float)bounceCountArray.get(i)/(float)clickCountArray.get(i));
			}
			
		}
		return bounceRateArray;
	}


	public ArrayList<Float> getBounceRateByTime(int interval, int timeSpent)
	{
		System.out.println("Getting bounce rate by time");
		ArrayList<Integer> bounceCountArray = this.getBounceNumberByTime(interval, timeSpent);
		ArrayList<Integer> clickCountArray = this.getClickNumber(interval);
		int cLength = bounceCountArray.size();
		int iLength = clickCountArray.size();
		if(cLength > iLength)
		{
			clickCountArray = ensureSize(clickCountArray, cLength);
			iLength = cLength;
		}
		else if(iLength > cLength)
		{
			bounceCountArray = ensureSize(bounceCountArray, iLength);
			cLength = iLength;
		}
		ArrayList<Float> bounceRateArray = new ArrayList<Float>(cLength);
		for(int i = 0; i < cLength; i++)
		{
			if(clickCountArray.get(i).equals(0))
			{
				bounceRateArray.add((float)bounceCountArray.get(i)/1f);
			}
			else
			{
				bounceRateArray.add((float)bounceCountArray.get(i)/(float)clickCountArray.get(i));
			}
			
		}
		return bounceRateArray;
	}
	
	public String[] getMetrics(ArrayList<Impression> impLog, ArrayList<Click> clickLog, ArrayList<Server> serverLog,
			boolean byPage, int bounceValue)
	{
		String[] metrix = new String[12];
		int impCount= impLog.size();
		metrix[0] = Integer.toString(impCount);
		int clickCount= clickLog.size();
		metrix[1] = Integer.toString(clickCount);
		//Unique number
		int uniqueCount = 0;
		HashSet<Long> userIds = new HashSet<Long>(clickCount);
		for(int i = 0; i < clickCount; i++)
		{
			long userId = clickLog.get(i).id;
			
			if(userIds.contains(userId))
			{
				
			}
			else
			{
				userIds.add(userId);
				uniqueCount++;
			}
		}
		metrix[2] = Integer.toString(uniqueCount);
		//Bounce number
		int bounceNumber = 0;
		if(byPage)
		{
			for(int i = 0; i < serverLog.size(); i++)
			{
				if(serverLog.get(i).page <= bounceValue)
				{
					bounceNumber++;
				}
			}
		}
		else
		{
			for(int i = 0; i < serverLog.size(); i++)
			{
				if(!serverLog.get(i).endDate.equals("n/a"))
				{
					LocalDateTime entryDate = LocalDateTime.from(fmt.parse(serverLog.get(i).date));
					LocalDateTime exitDate = LocalDateTime.from(fmt.parse(serverLog.get(i).endDate));
					
					if(Duration.between(entryDate, exitDate).getSeconds() <= bounceValue)
					{
						bounceNumber++;
					}
				}	
			}
		}		
		metrix[3] = Integer.toString(bounceNumber);
		//Conversion Number
		int conversionNumber = 0;
		for(Server h : serverLog)
		{
			if(h.conversion)
			{
				conversionNumber++;
			}
		}
		metrix[4] = Integer.toString(conversionNumber);
		//CTR
		float CTR = (float)clickCount/(float)impCount;
		metrix[5] = Float.toString(100 * CTR);
		//CTA
		float clickCost = 0f;
		for(Click c : clickLog)
		{
			clickCost += c.cost;
		}
		float CTA = clickCost/(float)conversionNumber;
		metrix[6] = Float.toString(CTA);
		//CPC
		float CPC = clickCost/(float)clickCount;
		metrix[7] = Float.toString(CPC);
		//CPM
		float impCost = 0;
		for(Impression c : impressionLog)
		{
			impCost += c.cost;
		}
		float CPM = impCost/(float) impCount * 1000f;
		metrix[8] = Float.toString(CPM);
		//Bounce Rate
		float bounceRate = (float)bounceNumber/(float)clickCount;
		metrix[9] = Float.toString(bounceRate * 100);
		
		//Total Imp cost
		metrix[10] = Float.toString(impCost);
		
		//Total Click cost
		metrix[11] = Float.toString(clickCost);
		
		
		return metrix;
	}
	
	public String getUpdatedBounceNumber(boolean byPage, int bounceValue)
	{
		//Bounce number
		int bounceNumber = 0;
		if(byPage)
		{
			for(int i = 0; i < serverLog.size(); i++)
			{
				if(serverLog.get(i).page <= bounceValue)
				{
					bounceNumber++;
				}
			}
		}
		else
		{
			for(int i = 0; i < serverLog.size(); i++)
			{
				if(!serverLog.get(i).endDate.equals("n/a"))
				{
					LocalDateTime entryDate = LocalDateTime.from(fmt.parse(serverLog.get(i).date));
					LocalDateTime exitDate = LocalDateTime.from(fmt.parse(serverLog.get(i).endDate));
							
					if(Duration.between(entryDate, exitDate).getSeconds() <= bounceValue)
					{
						bounceNumber++;						
					}
				}
			}
		}		
		return Integer.toString(bounceNumber);
	}
	
	public String getUpdatedBounceRate(boolean byPage, int bounceValue)
	{
		//Bounce number
		int bounceNumber = 0;
		if(byPage)
		{
			for(int i = 0; i < serverLog.size(); i++)
			{
				if(serverLog.get(i).page <= bounceValue)
				{
					bounceNumber++;
				}
			}
		}
		else
		{
			for(int i = 0; i < serverLog.size(); i++)
			{
				if(!serverLog.get(i).endDate.equals("n/a"))
				{
					LocalDateTime entryDate = LocalDateTime.from(fmt.parse(serverLog.get(i).date));
					LocalDateTime exitDate = LocalDateTime.from(fmt.parse(serverLog.get(i).endDate));
							
					if(Duration.between(entryDate, exitDate).getSeconds() <= bounceValue)
					{
						bounceNumber++;						
					}
				}
			}
		}		
		//Bounce Rate
		float bounceRate = (float)bounceNumber/(float)clickLog.size();
		return Float.toString(bounceRate * 100);
	}
	
}
