package decoder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Hashtable;

import com.opencsv.CSVReader;

public class Decoder implements DecoderInterface
{
	/**
	@Override
	public Hashtable<String, String>[] getData(String fileName) throws IOException
	{
		
		CSVReader reader = new CSVReader(new FileReader(fileName));
		
		//Getting the fields
		String[] keys = reader.readNext();
		Hashtable<String, String>[] result = new Hashtable[getLogSize(new File(fileName))];

		String[] nextLine;

		Hashtable<String, String> currentRecord;
		int i = 0;
		while ((nextLine = reader.readNext()) != null)
		{
			currentRecord = new Hashtable<String, String>();
			//System.out.println("Decoding imp log line " + i);
			for (int j = 0; j < keys.length; ++j)
			{
				currentRecord.put(keys[j], nextLine[j]);	
			}
					
			result[i] = currentRecord;
			i++;
		}
		
		System.out.println("Finish Decoding " + fileName);
		return result;
	
	}**/
	
	public ArrayList<Impression> getImpressionLogData(String fileName) throws IOException
	{
		
		CSVReader reader = new CSVReader(new FileReader(fileName));
		
		//Getting the fields
		String[] keys = reader.readNext();
		ArrayList<Impression> result = new ArrayList<Impression>(10000);

		String[] nextLine;

		int i = 0;
		while ((nextLine = reader.readNext()) != null)
		{
			//System.out.println("Decoding imp log line " + i);
					
			result.add(new Impression(nextLine));
			i++;
		}
		
		System.out.println("Finish Decoding " + fileName);
		return result;
	
	}
	
	public ArrayList<Click> getClickLogData(String fileName) throws IOException
	{
		
		CSVReader reader = new CSVReader(new FileReader(fileName));
		
		//Getting the fields
		String[] keys = reader.readNext();
		ArrayList<Click> result = new ArrayList<Click>(10000);

		String[] nextLine;

		int i = 0;
		while ((nextLine = reader.readNext()) != null)
		{
			//System.out.println("Decoding imp log line " + i);
					
			result.add(new Click(nextLine));
			i++;
		}
		
		System.out.println("Finish Decoding " + fileName);
		return result;
	
	}
	
	public ArrayList<Server> getServerLogData(String fileName) throws IOException
	{
		
		CSVReader reader = new CSVReader(new FileReader(fileName));
		
		//Getting the fields
		String[] keys = reader.readNext();
		ArrayList<Server> result = new ArrayList<Server>(10000);

		String[] nextLine;

		int i = 0;
		while ((nextLine = reader.readNext()) != null)
		{
			//System.out.println("Decoding imp log line " + i);
					
			result.add(new Server(nextLine));
			i++;
		}
		
		System.out.println("Finish Decoding " + fileName);
		return result;
	
	}
	
	/**
	private int getLogSize(File log) {
		
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(log));
			lnr.skip(Long.MAX_VALUE);
			int lines = lnr.getLineNumber();
			System.out.println(lines);
			lnr.close();
			return lines-1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}**/
	
}
