package scheduler;

public class TitleConflictException extends CustomException {
	public TitleConflictException()
	{
		message = "An event with this title already exists on that day";
	}
}
