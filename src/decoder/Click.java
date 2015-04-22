package decoder;

public class Click
{
	public long id;
	public String date;
	public float cost;
	
	public Click(String[] raw)
	{
		setDate(raw[0]);
		setID(Long.valueOf(raw[1]));
		setCost(raw[2]);
	}
	
	private void setID(long s)
	{
		//System.out.println(s);
		id = s;
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
