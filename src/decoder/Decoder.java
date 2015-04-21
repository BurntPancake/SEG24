package decoder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.opencsv.CSVReader;

public class Decoder implements DecoderInterface
{
	//TODO: Check if the log matches its name
	
	@Override
	public Hashtable<String, String>[] getData(String fileName) throws IOException
	{
		CSVReader reader = new CSVReader(new FileReader(fileName));
		
		//Getting the fields
		String[] keys = reader.readNext();
		ArrayList<Hashtable<String, String>> recordList = new ArrayList<Hashtable<String, String>>();

		String[] nextLine;
		while ((nextLine = reader.readNext()) != null)
		{
			System.out.println("Read line");
			Hashtable<String, String> currentRecord = new Hashtable<String, String>();
			if (keys.length == nextLine.length){ 
				for (int j = 0; j < keys.length; ++j)
					currentRecord.put(keys[j], nextLine[j]);	
			}
			recordList.add(currentRecord);
         }
		
		Hashtable<String, String>[] result = new Hashtable[recordList.size()];
	
		for(int i = 0; i < recordList.size(); i++)
		{
			result[i] = recordList.get(i);
		}
		System.out.println("Finish Decoding " + fileName);
		return result;
	
	}
	
	//The second argument can be used to check if the correct input is given
	//Not used currently;
	public CSVReader getRawData(String fileName, String checkName)  throws IOException
	{
		CSVReader reader = new CSVReader(new FileReader(fileName));
		//Getting the fields
		String[] keys = reader.readNext();
		
		return reader;
		
	}
	
	/**
	public CSVReader getClickLogRaw(String clickLogName)
	{
		return null;
	}**/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
