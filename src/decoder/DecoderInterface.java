package decoder;
import java.util.Hashtable;
import java.util.Map;


public interface DecoderInterface
{
	public void readFile(String filename);
	public Map<Integer, Hashtable<String, String>> getData();
}
