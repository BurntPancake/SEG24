package decoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.opencsv.CSVReader;

public class Decoder implements DecoderInterface
{
	
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
			Hashtable<String, String> currentRecord = new Hashtable<String, String>();
			for (int j = 0; j < keys.length; ++j)
			{
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
	
}
