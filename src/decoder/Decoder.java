package decoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Decoder implements DecoderInterface
{
	
	@Override
	public Hashtable<String, String>[] getData(String fileName) throws IOException
	{
		System.out.println("Getting data from " + fileName + "...");
		// Setting up input method
		BufferedReader csvDataReader = getLineReader(fileName);
		String[] keys = parseLine(csvDataReader.readLine());
		int entries = getLineCount(fileName, keys.length);
		
		String line;
		Hashtable<String, String>[] recordList = (Hashtable<String, String>[]) new Hashtable[entries-1];
		
		for (int i = 0; i < entries - 1; ++i) {
		
			line = csvDataReader.readLine();
			Hashtable<String, String> currentRecord = new Hashtable<String, String>();
			String[] values = parseLine(line);
			
			// If construct so that only full lines are read in.
			if (keys.length == values.length){ 
				for (int j = 0; j < keys.length; ++j)
					currentRecord.put(keys[j], values[j]);	
			}
			
			recordList[i] = currentRecord;
				
		}
		System.out.println("Getting data done!");
		return recordList;
	
	}
	
	private BufferedReader getLineReader(String fileName) throws IOException {
		return new BufferedReader(new FileReader (new File(fileName)));
	}
	
	private String[] parseLine(String line) {
		String[] fields = line.split(",");
		return fields;
	}
	
	private int getLineCount(String fileName, int keys) throws IOException {
		
		BufferedReader lineReader = getLineReader(fileName);
		int i = 0;
		String line;
		while ((line = lineReader.readLine()) != null) {
			if (parseLine(line).length == keys)
				i++;
		}
		
		return i;
		
	}
	
}
