package decoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Decoder implements DecoderInterface
{

	@Override
	public ArrayList<Hashtable<String, String>> getData(String fileName) throws IOException
	{
		// Setting up input method
		BufferedReader csvDataReader = getLineReader(fileName);
		
		ArrayList<Hashtable<String, String>> recordList = new ArrayList<Hashtable<String, String>>();
		String[] keys = parseLine(csvDataReader.readLine());
		String line;
		
		while ((line = csvDataReader.readLine()) != null) {
			
			Hashtable<String, String> currentRecord = new Hashtable<String, String>();
			String[] values = parseLine(line);
			
			for (int i = 0; i < keys.length; ++i) {
				currentRecord.put(keys[i], values[i]);
			}
			
			recordList.add(currentRecord);
			
		}
		// sort? 
		return null;
	}
	
	private BufferedReader getLineReader(String fileName) throws IOException {
		return new BufferedReader(new FileReader (new File(fileName)));
	}
	
	private String[] parseLine(String line) {
		String[] fields = line.split(",");
		return fields;
	}
	
}
