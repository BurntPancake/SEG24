package calculator;

/**
 * 
 * @author Zd
 *
 */
public interface CalculatorInterface
{
	public Integer[] getImpressionNumber(int interval);
	public Integer[] getClickNumber(int interval);
	public Integer[] getUniqueNumber(int interval);
	public Integer[] getConversionNumber(int interval);
	public Float[] getImpressionCost(int interval);
	public Float[] getClickCost(int interval);
	public Float[] getCTR(int interval);
	public Float[] getCPA(int interval);
	public Float[] getCPC(int interval);
	public Float[] getCPM(int interval);
	
	public Integer[] getBounceNumber(int interval);//User defined Bounce
	public Float[] getBounceRate(int interval);
}
