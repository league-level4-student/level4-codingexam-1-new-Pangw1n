package scheduler;

public enum Day {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, SATURDAY, SUNDAY;
	
	private LinkedList<Event> events = new LinkedList<Event>();
	
	public void addEvent(String title, int hour, int min) throws InvalidTimeException, SchedulingConflictException, TitleConflictException
	{	
		Event newEvent = new Event(title, hour, min);
		Node<Event> current = events.getHead();
		
		while (current != null)
		{
			if (current.getValue().getTitle().equalsIgnoreCase(title))
			{
				throw new TitleConflictException();
			}
			else
			{
				current = current.getNext();
			}
		}
		current = events.getHead();
		
		if (current == null)
		{
			events.add(newEvent);
			return;
		}
		else
		{
			while (current != null)
			{
				if (current.getValue().getHour() < newEvent.getHour())
				{
					current = current.getNext();
					continue;
				}
				else if (current.getValue().getMinute() < newEvent.getMinute())
				{
					current = current.getNext();
					continue;
				}
				else if (current.getValue().getHour() == newEvent.getHour() && current.getValue().getMinute() == newEvent.getMinute())
				{
					throw new SchedulingConflictException();
				}
				else
				{
					Node<Event> newNode = new Node<Event>(newEvent);
					if (current.getPrev() == null)
					{
						events.setHead(newNode);
					}
					newNode.setNext(current);
					if (current.getPrev() != null)
					{
						newNode.setPrev(current.getPrev());
						current.getPrev().setNext(newNode);
					}
					current.setPrev(newNode);
					return;
				}
			}
			events.add(newEvent);
		}
	}
	
	public LinkedList<Event> getEvents()
	{
		return events;
	}
}
