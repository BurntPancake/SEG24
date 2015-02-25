package decoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public interface DecoderInterface
{
	public Hashtable<String, String>[] getData(String fileName) throws IOException;
}
