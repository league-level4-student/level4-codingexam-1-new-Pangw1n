package scheduler;

public class SchedulingConflictException extends CustomException {
	public SchedulingConflictException()
	{
		message = "An event is already scheduled at that time";
	}
}
