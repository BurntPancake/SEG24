package calculator;

public class DBCalculatorTest
{
	public static void main(String[] args)
	{
		DBCalculator dbc = new DBCalculator("testDB", "ExampleInputData/click_log.csv", 
				"ExampleInputData/impression_Log.csv", "ExampleInputData/server_Log.csv");
	}
}
