package decoder;

import java.io.IOException;
import java.util.ArrayList;

public class DecoderImpTest
{
	public static void main(String[] args) throws IOException
	{
		Decoder dec = new Decoder();
		ArrayList<Impression> imps;
		Long x = System.currentTimeMillis();
		imps = dec.getImpressionLogData("LongInput/impression_log.csv");
		for(Impression i : imps)
		{
			System.out.println(i.date);
			System.out.println(i.age);
			System.out.println(i.context);
			System.out.println(i.cost);
			System.out.println(i.gender);
			System.out.println(i.income);
			System.out.println();
		}
		System.out.println(System.currentTimeMillis() - x);
	}
}
