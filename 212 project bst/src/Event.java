/***************
	    CLASS: PhaseOne.java
	    CSC212 Data structures - Project phase I
	    Fall 2023
	    EDIT DATE:
	    17-10-2023
	    TEAM:
	    Data Craftsmen.
	    AUTHORS:
	    authors-names:
	    Abdulaziz Almousa , (443101909).
	    Faisal Mohammed Alomran , (443102216).
	    Mohammed Alrabah , (437106719).
	    *************/

public class Event implements Comparable<Event> {
	private String eventTitle;
	private String dateAndTime;
	private String location;
	private BST<Contact> involvedContacts;

	public Event(String eventTitle, String dateAndTime, String location) {
		this.eventTitle = eventTitle;
		this.dateAndTime = dateAndTime;
		this.location = location;
		involvedContacts = new BST<Contact>();

	}

	public String getEventTitle() {
		return eventTitle;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public String getLocation() {
		return location;
	}

	public BST<Contact> getInvolvedContacts() {
		return involvedContacts;
	}

	public void Display() {

		System.out.println("\nEvent title: " + eventTitle);

		System.out.println("Contact's name:");
		//involvedContacts.findFirst();
		IntWrapper count = new IntWrapper(0);
		
			// for print all names of contact inside the event
		involvedContacts.PrintAllNamesOfcontact(count,involvedContacts.root );
			
		
		System.out.println("Event date and time (DD/MM/YYYY HH:MM): " + dateAndTime);// 1
		System.out.println("Event location: " + location);// 1

		System.out.println("\n");// 1
	}// bigO(n)

	@Override
	public int compareTo(Event o) {
		String te = o.eventTitle.toLowerCase();// n
		return eventTitle.compareTo(te);// n
		// bigO(n)
	}

}
