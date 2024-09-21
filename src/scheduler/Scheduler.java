package scheduler;

import java.util.Scanner;

/*
 * Objective: Create a weekly scheduling application.
 * 
 * You may create any additional enums, classes, methods or variables needed
 * to accomplish the requirements below:
 * 
 * - You should use an array filled with enums for the days of the week and each
 *   enum should contain a LinkedList of events that includes a time and what is 
 *   happening at the event.
 * 
 * - The user should be able to to interact with your application through the
 *   console and have the option to add events, view events or remove events by
 *   day.
 *   
 * - Each day's events should be sorted by chronological order.
 *  
 * - If the user tries to add an event on the same day and time as another event
 *   throw a SchedulingConflictException(created by you) that tells the user
 *   they tried to double book a time slot.
 *   
 * - Make sure any enums or classes you create have properly encapsulated member
 *   variables.
 */


public class Scheduler {
	
private LinkedList<Day> schedule;
private boolean fullClock;
private boolean quit;
Scanner scanner;
String input;


    public static void main(String[] args) {
    	new Scheduler().run();
    }
    
    public void run()
    {
    	scanner = new Scanner(System.in);
    	
    	input = "";
    	
    	do 
    	{
    		System.out.println("Use 24 hour clock? (y/n)");
    		input = scanner.nextLine();
    		fullClock = input.equalsIgnoreCase("y") ? true : false;
    	}
    	while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
    	
    	schedule = new LinkedList<Day>();
    	
    	Day[] days = Day.values();
    	for (int i = 0; i < days.length; i++)
    	{
    		schedule.add(days[i]);
    	}
    	
    	displayCalendar();
    	
    	do
    	{
    		System.out.println("Commands: Add, Remove, View, Event, Quit");
    		input = scanner.nextLine();
    		switch(input.toLowerCase())
    		{
    			case "add":
    				try {
        				addEvent();
					} catch (Exception e) {
						System.out.println("error");
						e.printStackTrace();
					}
    				break;
    			case "remove":
    				removeEvent();
    				break;
    			case "view":
    				displayCalendar();
    				break;
    			case "event":
    				viewEvent();
    				break;
    			case "quit":
    				quit = true;
    				break;
    		}
    	}
    	while (!quit);
    	scanner.close();
    }
    
    
    
    public void displayCalendar()
    {
    	Node<Day> current = schedule.getHead();
    	while (current != null)
    	{
    		System.out.println(current.getValue());
    		Node<Event> currentEvent = current.getValue().getEvents().getHead();
    		while (currentEvent != null)
    		{
    			System.out.println(" - " + (fullClock ? currentEvent.getValue().getTime24() : currentEvent.getValue().getTime12()) + " - " + currentEvent.getValue().getTitle());
    			currentEvent = currentEvent.getNext();
    		}
    		current = current.getNext();
    	}
    }
    
    public void addEvent()
    {
    	System.out.println("Enter a day: ");
    	String inputDay = scanner.nextLine();
    	
    	Node<Day> current = schedule.getHead();
    	Node<Day> day = null;
    	while (current != null)
    	{
    		if (current.getValue().toString().equalsIgnoreCase(inputDay))
    		{
    			day = current;
    			break;
    		}
    		else
    		{
    			current = current.getNext();
    		}
    	}
    	if (day == null)
    	{
    		System.out.println("invalid day");
    		return;
    	}
    		
    		
    	
    	System.out.println("Enter a title: ");
    	String inputTitle = scanner.nextLine();
    	
    	System.out.println("Enter a description: ");
    	String inputDescription = scanner.nextLine();
    	
    	System.out.println("Enter an hour: ");
    	int inputHour = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.println("Enter a minute: ");
    	int inputMin = scanner.nextInt();
    	scanner.nextLine();
    	
    	if (!fullClock)
    	{
    		System.out.println("Am or Pm");
    		String inputTime = scanner.nextLine();
    		boolean pm = inputTime.equalsIgnoreCase("pm") ? true : false;
    		
    		if (inputHour < 12 && pm)
    		{
    			inputHour += 12;
    		}
    		else if (inputHour == 12 && !pm)
    		{
    			inputHour = 24;
    		}
    	}
    	
		try {
			day.getValue().addEvent(inputTitle, inputDescription, inputHour, inputMin);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }
    
    public void removeEvent()
    {
    	System.out.println("Enter a day: ");
    	String inputDay = scanner.nextLine();
    	
    	Node<Day> currentDay = schedule.getHead();
    	Node<Day> day = null;
    	while (currentDay != null)
    	{
    		if (currentDay.getValue().toString().equalsIgnoreCase(inputDay))
    		{
    			day = currentDay;
    			break;
    		}
    		else
    		{
    			currentDay = currentDay.getNext();
    		}
    	}
    	if (day == null)
    	{
    		System.out.println("invalid day");
    		return;
    	}
    	
    	
    	
    	System.out.println("Enter an events title: ");
    	String inputTitle = scanner.nextLine();
    	Node<Event> current = day.getValue().getEvents().getHead();
    	int count = 0;
    	while (current != null)
    	{
    		if (current.getValue().getTitle().equalsIgnoreCase(inputTitle))
    		{
    			day.getValue().getEvents().remove(count);
    			return;
    		}
    		else
    		{
    			current = current.getNext();
    			count++;
    		}
    	}
    }
    
    public void viewEvent()
    {
    	System.out.println("Enter a day: ");
    	String inputDay = scanner.nextLine();
    	
    	Node<Day> currentDay = schedule.getHead();
    	Node<Day> day = null;
    	while (currentDay != null)
    	{
    		if (currentDay.getValue().toString().equalsIgnoreCase(inputDay))
    		{
    			day = currentDay;
    			break;
    		}
    		else
    		{
    			currentDay = currentDay.getNext();
    		}
    	}
    	if (day == null)
    	{
    		System.out.println("invalid day");
    		return;
    	}
    	
    	
    	
    	System.out.println("Enter an events title: ");
    	String inputTitle = scanner.nextLine();
    	Node<Event> current = day.getValue().getEvents().getHead();
    	while (current != null)
    	{
    		if (current.getValue().getTitle().equalsIgnoreCase(inputTitle))
    		{
    			System.out.println(current.getValue().getTitle() + " - " + (fullClock ? current.getValue().getTime24() : current.getValue().getTime12()));
    			System.out.println(" - " + current.getValue().getDescription());
    			return;
    		}
    		else
    		{
    			current = current.getNext();
    		}
    	}
    }
    
    public void editEvent()
    {
    	System.out.println("Enter a day: ");
    	String inputDay = scanner.nextLine();
    	
    	Node<Day> currentDay = schedule.getHead();
    	Node<Day> day = null;
    	while (currentDay != null)
    	{
    		if (currentDay.getValue().toString().equalsIgnoreCase(inputDay))
    		{
    			day = currentDay;
    			break;
    		}
    		else
    		{
    			currentDay = currentDay.getNext();
    		}
    	}
    	if (day == null)
    	{
    		System.out.println("invalid day");
    		return;
    	}
    	
    	
    	
    	System.out.println("Enter an events title: ");
    	String inputTitle = scanner.nextLine();
    	Node<Event> current = day.getValue().getEvents().getHead();
    	while (current != null)
    	{
    		if (current.getValue().getTitle().equalsIgnoreCase(inputTitle))
    		{
    			System.out.println("Edit: Title, Description, or Time");
    			switch(scanner.nextLine().toLowerCase())
    			{
    				case "title":
    					System.out.println("Enter a new title: ");
    					current.getValue().setTitle(scanner.nextLine());
    					return;
    				case "description":
    					System.out.println("Enter a new description: ");
    					current.getValue().setDescription(scanner.nextLine());
    					return;
    				case "time":
    					System.out.println("Enter an hour");
    					int inputHour = scanner.nextInt();
    					scanner.nextLine();
    					System.out.println("Enter a minute");
    					int inputMin = scanner.nextInt();
    					scanner.nextLine();
    					
    					if (fullClock)
    					{
        					try {
								current.getValue().setTime(inputHour, inputMin);
							} catch (InvalidTimeException e) {
								// TODO Auto-generated catch block
								System.out.println(e.message);
							}
    					}
    					else 
    					{
    						System.out.println("AM / PM");
    						boolean pm = scanner.nextLine().equalsIgnoreCase("pm") ? true : false;
    						try {
								current.getValue().setTime(inputHour, inputMin, pm);
								//sort
							} catch (InvalidTimeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    			}
    			System.out.println("return");
    			return;
    		}
    		else
    		{
    			current = current.getNext();
    		}
    	}

    }
}
