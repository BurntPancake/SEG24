package decoder;

public class Impression
{
	public String date;
	public Long id;
	public int gender; //It is politically correct to use int not boolean to represent gender.
	public int age;
	public int income;
	public int context;
	public float cost;
	
	public Impression(String[] raw)
	{
		setDate(raw[0]);
		setID(raw[1]);
		setGender(raw[2]);
		setAge(raw[3]);
		setIncome(raw[4]);
		setContext(raw[5]);
		setCost(raw[6]);
	}
	
	private void setDate(String date)
	{
		this.date = date;
	}
	
	private void setID(String id)
	{
		this.id = Long.getLong(id);
	}
	
	private void setGender(String s)
	{
		if(s == "Male")
		{
			gender = 0;
		}
		else if(s == "Female")
		{
			gender = 1;
		}
	}
	private void setAge(String s)
	{
		switch(s)
		{
			case "<25" : 
				age = 0; 
				break;
			case "25-34":
				age = 1;
				break;
			case "35-44" :
				age = 2;
				break;
			case "45-54" :
				age = 3;
				break;
			case ">54" :
				age = 4;
				break;
		}
	}
	private void setIncome(String s)
	{
		switch(s)
		{
			case "High" : 
				income = 0; 
				break;
			case "Medium":
				income = 1;
				break;
			case "Low" :
				income = 2;
				break;
		}
	}
	private void setContext(String s)
	{
		switch(s)
		{
			case "Blogs" : 
				context = 0; 
				break;
			case "News":
				context = 1;
				break;
			case "Shopping" :
				context = 2;
				break;
			case "Social Media" :
				context = 3;
				break;
		}
	}
	private void setCost(String s)
	{
		cost = Float.valueOf(s);
	}
}
