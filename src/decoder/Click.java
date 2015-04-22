package decoder;

public class Click
{
	public String date;
	public long id;
	public float cost;
	
	public Click(String[] raw)
	{
		setDate(raw[0]);
		setID(raw[1]);
		setCost(raw[2]);
	}
	
	private void setDate(String date)
	{
		this.date = date;
	}
	
	private void setID(String id)
	{
		this.id = Long.getLong(id);
	}
	
	private void setCost(String s)
	{
		cost = Float.valueOf(s);
	}
}
