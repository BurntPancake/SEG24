package decoder;

import java.io.IOException;

public class DecoderImpTest
{
	public static void main(String[] args) throws IOException
	{
		Decoder dec = new Decoder();
		Impression[] imps;
		imps = dec.getImpressionLogData("LongInput/impression_log.csv");
	}
}
