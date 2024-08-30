package scheduler;

public enum Day {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, SATURDAY, SUNDAY;
	
	private LinkedList<Event> events = new LinkedList<Event>();
	
	public void addEvent(String title, int hour, int min) throws InvalidTimeException
	{
		Event newEvent = new Event(title, hour, min);
		Node<Event> current = events.getHead();
		if (current == null)
		{
			events.add(newEvent);
		}
		else
		{
			while (current != null)
			{
				if (current.getValue().getHour() <= newEvent.getHour() && current.getValue().getMinute() <= newEvent.getMinute())
				{
					current = current.getNext();
				}
				else
				{
					Node<Event> newNode = new Node<Event>(newEvent);
					if (current.getPrev() == null)
					{
						events.setHead(newNode);
					}
					newNode.setNext(current);
					newNode.setPrev(current.getPrev());
					current.getPrev().setNext(newNode);
					current.setPrev(newNode);
				}
			}
		}
	}
}
