import java.util.Hashtable;
import java.util.Map;


public interface Decoder
{
	public void readFile(String filename);
	public Map<Integer, Hashtable<String, String>> getData();
}
