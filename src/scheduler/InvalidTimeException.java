package scheduler;

public class InvalidTimeException extends CustomException {
	public InvalidTimeException()
	{
		message = "Enter a valid time (1-12) or (1-24)";
	}
}
