package calculator;

/**
 * Should only be used by the calculator.
 * 
 * @author zd
 *
 */
public class CachedArrays
{
	ValueIntervalPair impressionNumber;

}

class ValueIntervalPair
{
	private Integer[] intValues;
	private Float[] floatValues;
	private int interval;
	
	public ValueIntervalPair(Integer[] intValues, int interval)
	{
		this.intValues = intValues;
		this.interval = interval;
	}
	public ValueIntervalPair(Float[] floatValues, int interval)
	{
		this.floatValues = floatValues;
		this.interval = interval;
	}
	
	public Integer[] getIntValues()
	{
		return intValues;
	}
	public Float[] getFloatValues()
	{
		return floatValues;
	}
	public int getInterval()
	{
		return interval;
	}

}