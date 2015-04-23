package calculator;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Hashtable;

import decoder.Click;
import decoder.Decoder;
import decoder.DecoderInterface;
import decoder.Impression;
import decoder.Server;

import org.junit.Test;

import decoder.Decoder;

public class CalculatorTest
{
	
	/*
	 * Checks that the calculator uses the interval correctly for every metric.
	 */
	@Test
	public void checkCorrectAmountOfAnswers() {
		
		DecoderInterface decoder = new Decoder();
		
		ArrayList<Impression> impressionRecords = null;
		ArrayList<Server> serverRecords = null;
		ArrayList<Click> clickRecords = null;
		
		try {
			impressionRecords = decoder.getImpressionLogData("ExampleInputData/impression_log.csv");
			serverRecords = decoder.getServerLogData("ExampleInputData/server_log.csv");
			clickRecords = decoder.getClickLogData("ExampleInputData/click_log.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Calculator calc = new Calculator(impressionRecords, clickRecords, serverRecords);
		
		ArrayList<Integer> answers = calc.getImpressionNumber(86400);
		assertEquals(13, answers.size());
		
		answers = calc.getImpressionNumber(3600);
		assertEquals(312, answers.size());
		
		answers = calc.getClickNumber(86400);
		assertEquals(13, answers.size());
		
		answers = calc.getClickNumber(3600);
		assertEquals(312, answers.size());
		
		answers = calc.getUniqueNumber(86400);
		assertEquals(13, answers.size());
		
		answers = calc.getUniqueNumber(3600);
		assertEquals(312, answers.size());
		
		answers = calc.getConversionNumber(86400);
		assertEquals(13, answers.size());
		
		answers = calc.getConversionNumber(3600);
		assertEquals(312, answers.size());
		
		ArrayList<Float> floatAnswers;
		
		floatAnswers = calc.getImpressionCost(86400);
		assertEquals(13, floatAnswers.size());
		
		floatAnswers = calc.getImpressionCost(3600);
		assertEquals(312, floatAnswers.size());
		
		floatAnswers = calc.getClickCost(86400);
		assertEquals(13, floatAnswers.size());
		
		floatAnswers = calc.getClickCost(3600);
		assertEquals(312, floatAnswers.size());
		
		floatAnswers = calc.getCTR(86400);
		assertEquals(13, floatAnswers.size());
		
		floatAnswers = calc.getCTR(3600);
		assertEquals(312, floatAnswers.size());
		
		floatAnswers = calc.getCPA(86400);
		assertEquals(13, floatAnswers.size());
		
		floatAnswers = calc.getCPA(3600);
		assertEquals(312, floatAnswers.size());
		
		floatAnswers = calc.getCPC(86400);
		assertEquals(13, floatAnswers.size());
		
		floatAnswers = calc.getCPC(3600);
		assertEquals(312, floatAnswers.size());
		
		floatAnswers = calc.getCPM(86400);
		assertEquals(13, floatAnswers.size());
		
		floatAnswers = calc.getCPM(3600);
		assertEquals(312, floatAnswers.size());
		
		answers = calc.getBounceNumberByPages(86400, 2);
		assertEquals(13, answers.size());
		
		answers = calc.getBounceNumberByPages(3600, 2);
		assertEquals(312, answers.size());
		
		answers = calc.getBounceNumberByTime(86400, 2);
		assertEquals(13, answers.size());
		
		answers = calc.getBounceNumberByTime(3600, 2);
		assertEquals(312, answers.size());
		
		floatAnswers = calc.getBounceRateByPages(86400, 2);
		assertEquals(13, floatAnswers.size());
		
		floatAnswers = calc.getBounceRateByPages(3600, 2);
		assertEquals(312, floatAnswers.size());
		
		floatAnswers = calc.getBounceRateByTime(86400, 2);
		assertEquals(13, floatAnswers.size());
		
		floatAnswers = calc.getBounceRateByTime(3600, 2);
		assertEquals(312, floatAnswers.size());
		
	}
	
	/*
	 * Check that the calculations being performed are correct, using smaller logs that can be calulated
	 * by hand.
	 */
	@Test
	public void checkCorrectAnswers() {
	
		DecoderInterface decoder = new Decoder();
		
		ArrayList<Impression> impressionRecords = null;
		ArrayList<Server> serverRecords = null;
		ArrayList<Click> clickRecords = null;
		
		try {
			impressionRecords = decoder.getImpressionLogData("ExampleInputData/impression_log_test.csv");
			serverRecords = decoder.getServerLogData("ExampleInputData/server_log_test.csv");
			clickRecords = decoder.getClickLogData("ExampleInputData/click_log_test.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Calculator calc = new Calculator(impressionRecords, clickRecords, serverRecords);
		
		ArrayList<Integer> answers = calc.getImpressionNumber(6);
		assertEquals(4, (int) answers.get(0));
		assertEquals(4, (int) answers.get(1));
		
		answers = calc.getClickNumber(120);
		assertEquals(4, (int) answers.get(0));
		assertEquals(4, (int) answers.get(1));
		
		answers = calc.getUniqueNumber(120);
		assertEquals(3, (int) answers.get(0));
		assertEquals(4, (int) answers.get(1));
		
		answers = calc.getConversionNumber(120);
		assertEquals(1, (int) answers.get(0));
		
		ArrayList<Float> floatAnswers;
		
		floatAnswers = calc.getImpressionCost(6);
		assertEquals(0.006107, (float) floatAnswers.get(0), 0.001);
		assertEquals(0.00619, (float) floatAnswers.get(1), 0.001);
		
		floatAnswers = calc.getClickCost(120);
		assertEquals(23.513105, (float) floatAnswers.get(0), 0.1);
		assertEquals(23.437326, (float) floatAnswers.get(1), 0.1);
		
		floatAnswers = calc.getCTR(120);
		assertEquals(0.5, (float) floatAnswers.get(0), 0.01);
		assertEquals(4.0, (float) floatAnswers.get(1), 0.01);
		
		floatAnswers = calc.getCPA(120);
		assertEquals(23.512105, (float) floatAnswers.get(0), 0.1);
		assertEquals(23.437326, (float) floatAnswers.get(1), 0.1);

		floatAnswers = calc.getCPC(120);
		assertEquals(5.8782763, (float) floatAnswers.get(0), 0.1);
		assertEquals(5.8593316, (float) floatAnswers.get(1), 0.1);

		floatAnswers = calc.getCPM(6);
		assertEquals(1.5267501, (float) floatAnswers.get(0), 0.1);
		assertEquals(1.5475, (float) floatAnswers.get(1), 0.1);

		answers = calc.getBounceNumberByPages(120, 2);
		assertEquals(1, (int) answers.get(0));
		assertEquals(1, (int) answers.get(1));
		
		answers = calc.getBounceNumberByTime(120, 2);
		assertEquals(1, (int) answers.get(0));
		
		floatAnswers = calc.getBounceRateByPages(120, 2);
		assertEquals(0.25, (float) floatAnswers.get(0), 0.1);
		assertEquals(0.25, (float) floatAnswers.get(1), 0.1);
	
		floatAnswers = calc.getBounceRateByTime(120, 2);
		assertEquals(0.25, (float) floatAnswers.get(0), 0.1);
		
	}
	/*
	 * Black box testing that makes use of two different data logs, that *should* still return the same results.
	 */
	@Test
	public void partitionTests() {
		DecoderInterface decoder = new Decoder();
		
		ArrayList<Impression> impressionRecords = null;
		ArrayList<Server> serverRecords = null;
		ArrayList<Click> clickRecords = null;
		
		try {
			impressionRecords = decoder.getImpressionLogData("ExampleInputData/impression_partition1.csv");
			serverRecords = decoder.getServerLogData("ExampleInputData/server_partition1.csv");
			clickRecords = decoder.getClickLogData("ExampleInputData/click_partition1.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Calculator calc1 = new Calculator(impressionRecords, clickRecords, serverRecords);
		
		impressionRecords = null;
		serverRecords = null;
		clickRecords = null;
		
		try {
			impressionRecords = decoder.getImpressionLogData("ExampleInputData/impression_partition2.csv");
			serverRecords = decoder.getServerLogData("ExampleInputData/server_partition2.csv");
			clickRecords = decoder.getClickLogData("ExampleInputData/click_partition2.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Calculator calc2 = new Calculator(impressionRecords, clickRecords, serverRecords);
	
		assertEquals((int) calc1.getImpressionNumber(6).get(0), (int) calc2.getImpressionNumber(6).get(0));
		assertEquals((int) calc1.getImpressionNumber(6).get(1), (int) calc2.getImpressionNumber(6).get(1));
		
		assertEquals((int) calc1.getImpressionNumber(12).get(0), (int) calc2.getImpressionNumber(12).get(0));

		assertEquals((int) calc1.getUniqueNumber(120).get(0), (int) calc2.getUniqueNumber(120).get(0));
		assertEquals((int) calc1.getUniqueNumber(120).get(1), (int) calc2.getUniqueNumber(120).get(1));

		assertEquals((int) calc1.getUniqueNumber(240).get(0), (int) calc2.getUniqueNumber(240).get(0));

		assertEquals((int) calc1.getClickNumber(120).get(0), (int) calc2.getClickNumber(120).get(0));
		assertEquals((int) calc1.getClickNumber(120).get(1), (int) calc2.getClickNumber(120).get(1));

		assertEquals((int) calc1.getClickNumber(240).get(0), (int) calc2.getClickNumber(240).get(0));
		
		assertEquals((int) calc1.getConversionNumber(120).get(0), (int) calc2.getConversionNumber(120).get(0));
		assertEquals((int) calc1.getConversionNumber(120).get(1), (int) calc2.getConversionNumber(120).get(1));

		assertEquals((int) calc1.getConversionNumber(240).get(0), (int) calc2.getConversionNumber(240).get(0));
	
	}
	
}
