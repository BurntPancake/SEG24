package decoder;

import java.io.IOException;

public class DecoderImpTest
{
	public static void main(String[] args) throws IOException
	{
		Decoder dec = new Decoder();
		Impression[] imps;
		Long x = System.currentTimeMillis();
		imps = dec.getImpressionLogData("LongInput/impression_log.csv");
		System.out.println(System.currentTimeMillis() - x);
	}
}
