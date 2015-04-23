package decoder;

public class Click
{
	public long id;
	public String date;
	public float cost;
	
	public Click(String[] raw)
	{
		setDate(raw[0]);
		setID(raw[1]);
		setCost(raw[2]);
	}
	
	private void setID(String s)
	{
		//System.out.println(s);
		id = Long.parseLong(s);
	}
	
	private void setDate(String s)
	{
		date = s;
	}
	
	private void setCost(String s)
	{
		cost = Float.valueOf(s);
	}
	
}
