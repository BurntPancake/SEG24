package decoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public interface DecoderInterface
{
	//public Hashtable<String, String>[] getData(String fileName) throws IOException;
	public ArrayList<Impression> getImpressionLogData(String fileName) throws IOException;
	public ArrayList<Click> getClickLogData(String fileName) throws IOException;
	public ArrayList<Server> getServerLogData(String fileName) throws IOException;
}
