package decoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public interface DecoderInterface
{
	//public Hashtable<String, String>[] getData(String fileName) throws IOException;
	public Impression[] getImpressionLogData(String fileName) throws IOException;
	public Click[] getClickLogData(String fileName) throws IOException;
	public Server[] getServerLogData(String fileName) throws IOException;
}
