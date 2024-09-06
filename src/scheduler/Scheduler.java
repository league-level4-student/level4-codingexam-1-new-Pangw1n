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
    		System.out.println("Commands: Add, View");
    		input = scanner.nextLine();
    		switch(input.toLowerCase())
    		{
    			case "add":
    				addEvent();
    				break;
    			case "view":
    				displayCalendar();
    				break;
    		}
    	}
    	while (!quit);
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
    	String inputDay = scanner.next();
    	scanner.nextLine();
    	
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
    		addEvent();
    		return;
    	}
    		
    		
    	
    	System.out.println("Enter a title: ");
    	String inputTitle = scanner.nextLine();
    	
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
    		boolean pm = inputTime.toLowerCase() == "pm" ? true : false;
    		
    		if (inputHour < 12 && pm)
    		{
    			inputHour += 12;
    		}
    		if (inputHour == 12 && !pm)
    		{
    			inputHour = 0;
    		}
    	}
    	
		try {
			day.getValue().addEvent(inputTitle, inputHour, inputMin);
		} catch (InvalidTimeException | SchedulingConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
