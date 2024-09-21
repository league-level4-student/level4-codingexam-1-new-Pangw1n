package scheduler;

public abstract class CustomException extends Exception{
	String message;
	
	public String getMessage()
	{
		return message;
	}
}
