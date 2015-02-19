package calculator;

import java.util.Hashtable;
import java.util.Map;

/**
 * 
 * 
 * @author Zd
 *
 */
public class Calculator implements CalculatorInterface
{
	private Map<Integer, Hashtable<String, String>> impressionLog;
	private Map<Integer, Hashtable<String, String>> clickLog;
	private Map<Integer, Hashtable<String, String>> serverLog;
	
	public Calculator(Map<Integer, Hashtable<String, String>> impressionLog, 
			Map<Integer, Hashtable<String, String>> clickLog,
			Map<Integer, Hashtable<String, String>> serverLog)
	{
		this.impressionLog = impressionLog;
		this.clickLog = clickLog;
		this.serverLog = serverLog;
	}
	
	@Override
	public int[] getImpressionNumber(int interval)
	{
		return null;
	}

	@Override
	public int[] getClickNumber(int interval)
	{
		// TODO Auto-generated method stub
		return null;
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
