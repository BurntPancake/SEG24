package calculator;

/**
 * All times are in the unit second
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
	/**
	 *  average number of clicks per impression
	 * @param interval
	 * @return
	 */
	public Float[] getCTR(int interval);
	/**
	 * average cost per conversion
	 * @param interval
	 * @return
	 */
	public Float[] getCPA(int interval);
	/**
	 *  Average cost per click
	 * @param interval
	 * @return
	 */
	public Float[] getCPC(int interval);
	/**
	 * Average cost per thousand impressions
	 * @param interval
	 * @return
	 */
	public Float[] getCPM(int interval);

	//User defined bounces
	/**
	 * if smaller or equal => count as a bounce
	 * @param interval
	 * @param pageViewed
	 * @return
	 */
	public Float[] getBounceRateByPages(int interval, int pageViewed);
	/**
	 * if smaller or equal => count as a bounce
	 * @param interval
	 * @param pageViewed
	 * @return
	 */
	public Float[] getBounceRateByTime(int interval, int timeSpent);
	/**
	 * if smaller or equal => count as a bounce
	 * @param interval
	 * @param pageViewed
	 * @return
	 */
	public Integer[] getBounceNumberByPages(int interval, int pageViewd);
	/**
	 * if smaller or equal => count as a bounce
	 * @param interval
	 * @param pageViewed
	 * @return
	 */
	public Integer[] getBounceNumberByTime(int interval, int timeSpent);

	/**
	 * Including small excluding big.
	 * 
	 * 0.002 cost interval ->
	 * [0.000, 0.002)
	 * [0.002, 0.004)
	 * @param costInterval
	 * @return
	 */
	public Integer[] getClickCostDistribution(float costInterval);
	public Float[] getHistogramClicks();
}
