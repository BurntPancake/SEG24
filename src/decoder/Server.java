package decoder;

public class Server
{
	public String entryDate;
	public String endDate;
	public long id;
	public int page;
	public boolean conversion;
	
	public Server(String[] raw)
	{
		setEntryDate(raw[0]);
		setID(raw[1]);
		setEndDate(raw[2]);
		setPage(raw[3]);
		setConversion(raw[4]);
	}
	
	private void setEntryDate(String date)
	{
		this.entryDate = date;
	}
	
	private void setEndDate(String date)
	{
		this.endDate = date;
	}
	
	private void setID(String id)
	{
		this.id = Long.getLong(id);
	}
	
	private void setPage(String s)
	{
		this.page = Integer.parseInt(s);
	}
	
	private void setConversion(String s)
	{
		switch(s)
		{
		case "Yes" : 
			this.conversion = true;
			break;
		case "No" :
			this.conversion = false;
			break;
		}
	}
	
}
