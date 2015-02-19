package calculator;

/**
 * 
 * @author Zd
 *
 */
public interface CalculatorInterface
{
	public int[] getImpressionNumber(int interval);
	public int[] getClickNumber(int interval);
	public int[] getUniqueNumber(int interval);
	
	public int[] getBounceNumber(int interval);//User defined Bounce
	
	public int[] getConversionNumber(int interval);
	public int[] getImpressionCost(int interval);
	public int[] getClickCost(int interval);
	public int[] getCTR(int interval);
	public int[] getCPA(int interval);
	public int[] getCPC(int interval);
	public int[] getCPM(int interval);
	public int[] getBounceRate(int interval);
}
