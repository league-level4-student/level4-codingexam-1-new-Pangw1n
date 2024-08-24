package scheduler;

public class Event{
	//hour 0 = 12 am / 24
	private String title;
	private int hour;
	private int min;
	
	public Event(String title, int hour, int min) throws InvalidTimeException
	{
		this.title = title;
		setTime(hour, min);
	}
	
	
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	
	
	//24 hour
	public void setTime(int hour, int min) throws InvalidTimeException
	{
		if (hour <= 0 || hour > 24 || min < 0 || min >= 60)
		{
			throw new InvalidTimeException();
		}
		hour = hour % 24;
		this.hour = hour;
		this.min = min;
	}
	
	//12 hour
	public void setTime(int hour, int min, boolean pm) throws InvalidTimeException
	{
		if (hour <= 0 || hour > 12 || min < 0 || min >= 60)
		{
			throw new InvalidTimeException();
		}
		hour = hour % 12;
		this.hour = pm ? hour + 12: hour;
		this.min = min;
	}
	
	public String getTime12()
	{
		int displayHour = hour % 12;
		displayHour = displayHour == 0 ? 12 : displayHour;
		String ampm = hour < 12 ? "am" : "pm";
		return displayHour + ":" + min + " " + ampm; 
	}
	
	public String getTime24()
	{
		int displayHour = hour;
		displayHour = displayHour == 0 ? 24 : displayHour;
		return displayHour + ":" + min;
	}
	
	
	
	public void setHour(int hour)
	{
		this.hour = hour;
	}
	
	public void setMinute(int min)
	{
		this.min = min;
	}
	
	public int getHour()
	{
		return hour;
	}
	
	public int getMinute()
	{
		return min;
	}
}
