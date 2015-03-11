package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

import org.junit.Test;

import calculator.Calculator;
import decoder.Decoder;
import decoder.DecoderInterface;

public class CalculatorTest
{

	@Test
	public void test()
	{
		DecoderInterface dec = new Decoder();
		try
		{
			Hashtable<String, String>[] impressionData = dec.getData("ExampleInputData/impression_log_test.csv");
			Hashtable<String, String>[] clickData = dec.getData("ExampleInputData/click_log_test.csv");
			Hashtable<String, String>[] serverData = dec.getData("ExampleInputData/server_log_test.csv");
			Calculator calc = new Calculator(impressionData, clickData, serverData);
			
			//Test counting entries ability
			assertTrue(Arrays.equals(new Integer[]{1,2,1,0,2,2}, calc.getImpressionNumber(2)));
			assertTrue(Arrays.equals(new Integer[]{6,2}, calc.getImpressionNumber(10)));
			
			//Test counting unique IDs ability
			assertTrue(Arrays.equals(new Integer[]{7}, calc.getUniqueNumber(3600)));
			
			//Test counting entries with specific filed ability
			assertTrue(Arrays.equals(new Integer[]{1}, calc.getConversionNumber(3600)));
			
			//Test get cost ability
			assertTrue(Arrays.equals(new Float[]{(float) (11.794442+11.718663+11.718663+11.718663)}, calc.getClickCost(3600)));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}