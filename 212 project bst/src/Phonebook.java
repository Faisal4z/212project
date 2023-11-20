
/***************
	    CLASS: PhaseOne.java
	    CSC212 Data structures - Project phase I
	    Fall 2023
	    EDIT DATE:
	    
	    TEAM:
	    Data Craftsmen.
	    AUTHORS:
	    authors-names:
	    Abdulaziz Almousa , (443101909).
	    Faisal Mohammed Alomran , (443102216).
	    Mohammed Alrabah , (437106719).
	    *************/
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.management.relation.InvalidRoleInfoException;

public class Phonebook {

	private BST<Contact> BstOfContact;
	private BST<Event> BstOfevent;

	Scanner textInput = new Scanner(System.in);
	Scanner intInput = new Scanner(System.in);

	Phonebook() {
		BstOfContact = new BST<Contact>();
		BstOfevent = new BST<Event>();
	}

	public void Menu() {
		try {

			boolean Flag = true;
			while (Flag) {
				System.out.println("\nPlease choose an option:");
				System.out.println("1.Add a contact");
				System.out.println("2.Search for a contact");
				System.out.println("3.Delete a contact");
				System.out.println("4.Schedule an event/appointment");
				System.out.println("5.Add a contact to the Event");
				System.out.println("6.Print event detils");
				System.out.println("7.Print contacts by first name");
				System.out.println("8.Print all events alphabetically");
				System.out.println("9.Delete Event");
				System.out.println("10.Exit");
				System.out.print("\nEnter your choice: ");

				int x = intInput.nextInt();
				intInput.nextLine();
				if (x <= 0 || x > 10)
					throw new Exception();
				System.out.println("");
				switch (x) {

				case 1:
					addingContct();
					break;
				case 2:
					searchForContact();

					break;
				case 3:
					deletingContacts();

					break;
				case 4:
					scheduleEvent();
					break;
				case 5:
					addContactsToAnEvent();
					break;
				case 6:
					printEventDetails();
					break;
				case 7:
					printCountactsByFirstName();

					break;
				case 8:
					printEventAlphaberically();
					break;
				case 9:
					deletingEvent();
					break;
				case 10:
					System.out.print("\nGoodbye!");

					Flag = false;
					return;

				default:

					System.out.println("\nPlease Enter a valid number!\n");
					break;
				}
			}
		} catch (Exception e) {
			intInput.nextLine();

			System.out.println("\nPlease Enter a valid number!\n");

			Menu();
		}
	}

	public void deletingEvent() {
		if (BstOfevent.empty()) {// 1
			System.out.println("You don't have a Event yet\n");// 1
			return;// 1
		}
		System.out.print("enter the Event title: ");// 1
		String EventTitle = textInput.nextLine();// 1
		textInput.nextLine();// 1

		boolean deleted = BstOfevent.remove_key(EventTitle);
		if (deleted)
			System.out.println("\nEvent deleted susccessfully!\n");// 1
		else
			System.out.println("their is no Event with this Title try again\n");// 1

	}

	public void searchForContact() {// 1
		try {// 1

			System.out.println("Enter search criteria:");// 1
			System.out.println("1.Name");// 1
			System.out.println("2.Phone Number");
			System.out.println("3.Email Address");
			System.out.println("4.Address");// 1
			System.out.println("5.Birthday");// 1
			System.out.println("\nEnter your choise: ");// 1
			int x = intInput.nextInt();// 1
			intInput.nextLine();
			if (x < 1 || x > 5)// 1
				throw new Exception();// 1

			System.out.print("Enter the value: ");// 1

			String text = textInput.nextLine();// 1

			if (x == 1) {
				if (BstOfContact.findkey(text)) {
					BstOfContact.retrieve().Display();
					return;
				}
			} else {
				BooleanWrapper Exist = new BooleanWrapper(false);
				BstOfContact.searchForContact(x, text, BstOfContact.root, Exist);
				if (Exist.getValue()) {
					BstOfContact.retrieve().Display();
					return;
				} else {
					System.out.println("\nthere is no cnntact that matches the value you enterd");// 1
				}

			}

		} catch (Exception e) {// 1
			System.out.println("\nthis is not currect number!! \ntry again");// 1

		}
		// bigO(n^2)
	}

//aziz
	public void addingContct() {
		// this metode well adding a contact if it is never be Exist in phoonbookList
		// done
		/* 1 */ System.out.print("Enter the contact's name: ");
		// 1
		/* 2 */ String name = textInput.nextLine();
		// 1
		/* 3 */System.out.print("Enter the contact's phone number: ");
		// 1
		/* 4 */String phone = textInput.nextLine();
		/* 5 */System.out.print("Enter the contact's email address: ");
		// 1
		/* 6 */String email = textInput.nextLine();
		// 1
		/* 7 */System.out.print("Enter the contact's address: ");
		// 1
		/* 8 */String address = textInput.nextLine();
		// 1
		/* 9 */System.out.print("Enter the contact's birthday: ");
		// 1
		/* 10 */String birthday = textInput.nextLine();
		// 1
		/* 11 */System.out.print("Enter any notes for the contact: ");
		// 1
		/* 12 */String notes = textInput.nextLine();
		// 1
		/* 13 */System.out.println("");
		// 1
		/* 14 */if (name == "" || phone == "" || phone.length() != 10 || phone.charAt(0) != '0') {
			// try to perform the number is currect
			// 1
			/* 15 */ System.out.println("The entered data is incomplete try to add name and phone number or wrong!\n");
			// 1
			/* 16 */return;
			// 1
			/* 17 */}
		/* 18 */Contact c = new Contact(name, phone, email, address, birthday, notes);// 1

		/* 19 */if (BstOfContact.findkey(name)) {
			// n^2
			/* 20 */ System.out.println("This countact is already exist\n");
			// 1
			/* 21 */ return;
			// 1
			/* 22 */}

		/* 23 */BstOfContact.insert(c.getName(), c);
		// n^2
		/* 24 */ System.out.println("Contact added successfully!\n");// 1

	}

	public void addContactsToAnEvent() {// this method well add a contact to exist event
		try {
			if (!BstOfevent.empty()) {

				System.out.print("Please enter the title of the event: ");
				String eventTitle = textInput.nextLine();

				if (!BstOfevent.findkey(eventTitle)) {
					System.out.println("\nthe event does not exist\n");
					return;
				}
				else if(BstOfevent.retrieve().getEventOrAppointment()==0) {
					System.out.print("this is an appointment so you can't");
					return;
				}
				Event e = BstOfevent.retrieve();

				System.out.print("Please enter the name of the contact: ");
				String contactname = textInput.nextLine();

				if (!BstOfContact.findkey(contactname)) {
					System.out.println("\nthe contact does not exist\n");
					return;
				}
				Contact c = BstOfContact.retrieve();
				BooleanWrapper Exist = new BooleanWrapper(false);
				// this loop to cheek there is conflict or not
				BstOfevent.inOrderCheekingtheDate(contactname, e.getDateAndTime(), BstOfevent.root, Exist);
				if (Exist.getValue()) {// 1
					System.out.println("This event can't be scheduled because " + contactname
							+ " is committed to an event at this date and time: " + e.getDateAndTime());// 1
					return;// 1
				}

				e.getInvolvedContacts().insert(c.getName(), c);// n^2
				System.out.println("\nThe contact has been added successfully\n");// n

			} else// n
				System.out.println("\nthere is no event yet \n");// n

		} catch (InputMismatchException x) {// n
			System.out.println("Invalid input!!");// n
		}
	}//

	public void scheduleEvent() {// this method well create an event and add one contact insid it
		String evnTitle, conName, dateAndTime, location;// 1
		int isEventOrAppointent;
		try {// 1

			System.out.print("Enter event title: ");// 1

			evnTitle = textInput.nextLine();// 1

			System.out.print("Enter event/appointment date and time (dd/mm/yyyy hh:mm): ");
			dateAndTime = textInput.nextLine();
			if (dateAndTime.length() < 15 || dateAndTime.length() > 17) {// 1
				System.out.print("\nthe date does't currect try again with this format (dd/mm/yyyy hh:mm)\n");// 1
				return;// 1
			}

			System.out.print("Enter contact name: ");// 1

			conName = textInput.nextLine();// 1

			System.out.print("Enter event location: ");// 1

			location = intInput.nextLine();// 1
			//textInput.nextLine();
			System.out.println("enter 1 if you would like to book and event or 0 if you want to book an appointment");
			isEventOrAppointent = intInput.nextInt();
			if(isEventOrAppointent!=0 || isEventOrAppointent!=0) {
				throw new InputMismatchException("this is an invalid input");
			}
			if (!BstOfContact.findkey(conName)) {// nLog(n)
				System.out.println("\nThe contact doesn't exist in this phoebook!!\n");// 1
				return;// 1
			} else {// 1
				Contact c = BstOfContact.retrieve();
				if (!BstOfevent.empty()) {// 1

					BooleanWrapper Exist = new BooleanWrapper(false);

					// this Method to check if there is conflict
					BstOfevent.current = BstOfevent.root;
					BstOfevent.inOrderCheekingtheDate(conName, dateAndTime, BstOfevent.root, Exist);
					if (Exist.getValue()) {
						System.out.println("This event can't be scheduled because " + conName
								+ " is committed to an event at this date and time: " + dateAndTime);// n^2
						return;
					}
				}

				Event newEvent = new Event(evnTitle, dateAndTime, location, isEventOrAppointent);// 1
				
				newEvent.getInvolvedContacts().insert(c.getName(), c);// nLog(n)
				BstOfevent.insert(newEvent.getEventTitle(), newEvent);// nLog(n)

				System.out.println("\nEvent scheduled successfully!\n");// 1
				// O()

			}

		} catch (Exception e) {
			System.out.println("Invalid input!!");
		}

	}

	public void printCountactsByFirstName() {

		System.out.print("Enter the first name: ");// 1
		String t = textInput.nextLine();// 1
		// textInput.nextLine();
		BooleanWrapper flag = new BooleanWrapper(false);

		if (!BstOfContact.empty()) {// 1

			BstOfContact.PrintByFirstName(t, BstOfContact.root, flag);

			if (!flag.getValue()) {// 1 // if does't find contact start with user input
				System.out.println("\nThere is no contact strting with this name\n");// 1
			}
		} else// 1
			System.out.println("\nThere is no contact added yet\n");// 1

	}
/* we can put it in the menu if you want
 * 
	public void printAllCountacts() {
		// حاليا هذي مؤقته فقط للإختبار
		if (!BstOfContact.empty()) {// 1
			// BstOfContact.findFirst();// 1
			while (BstOfContact.current != null) {// n+1
				BstOfContact.retrieve().Display();// n
				// BstOfContact.findNext();// n
			}

		} else// 1
			System.out.println("There is no contact strting with this name\n");// 1
		// bigO(n)
	}
*/
	public void printEventDetails() {

		try {// 1
			if (BstOfevent.empty()) {// 1
				System.out.println("\nyou have not added an event yet\n");// 1
				return;// 1
			}
			System.out.println();// 1

			System.out.println("Enter search criteria: \n1.contact name \n2.Event title");// 1 // firstly i asked the
																							// user to input the
																							// criteria of search

			// this boolean variable for checking if the contact is found or not
			BooleanWrapper flag = new BooleanWrapper(false); // 1

			System.out.print("\nEnter your choice:");// 1
			int choice = intInput.nextInt();// 1
			textInput.nextLine();
			if (choice < 1 || choice > 2)// 1
				throw new Exception();// 1
			// if the user looking for search by contact's name
			if (choice == 1) {// 1
				System.out.print("\nEnter contact's name: ");// 1
				String contactName = textInput.nextLine();// 1

				BstOfevent.PrintEventMatchesContact(contactName, BstOfevent.root, flag);// this inOrder methode for
																						// print all event that include
																						// the contact name
				// n^2

				// if you didn't find the event
				if (!flag.getValue()) {// 1
					System.out.println("their is no event accoiated with this contact \ntry again!");// 1
					return;// 1
				}

			} else if (choice == 2) {// 1
				// if the user looking for search by event's title
				System.out.print("\nEnter the event title: ");// 1
				String eventName = textInput.nextLine();// 1

				// if their is no event match with this title
				if (BstOfevent.findkey(eventName)) {// nLog(n)
					BstOfevent.retrieve().Display();// n
					return;// 1
				} else {// 1
					System.out.println("their is no event by this name\ntry again");// 1
				}
			}

		} catch (Exception e) {// 1
			System.out.println("this is not currect number!! \ntry again!");// 1

		}
		// bigO()
	}

	public void printEventAlphaberically() {
		if (BstOfevent.empty()) {// 1
			System.out.println("the list is empty add events please ");// 1
			return;// 1
		}

		BstOfevent.PrintAll(BstOfevent.root);//inOrder

	}

// bigO()
	public void deletingContacts() {

		if (BstOfContact.empty()) {// 1
			System.out.println("you don't have a contact yet\n");// 1
			return;// 1
		}
		System.out.print("\nenter the contact's name: ");// 1
		String contactName = textInput.nextLine();// 1
		

		// if their is no contact by this name
		if (!BstOfContact.findkey(contactName)) {//
			System.out.println("\ntheir is no contact by this name try again!");// 1
			return;// 1
		} else {// 1
			// the contact is exist

			BstOfContact.retrieve().Display();// 1

			BstOfContact.remove_key(contactName);// 1
			System.out.println("\nContact deleted susccessfully!\n");// 1
			// the contact has been removed

			if (BstOfevent.empty()) {// 1
				return;// 1
			}
			// this method well pass on all event to ceeck if there is a contact inside the
			// event well delete and
			// after delete it if number of contact inside the event = 0 well delete the
			// event also

			BstOfevent.inOrderDeletingcountacts(contactName, BstOfevent.root);

			// bigO()
		}
	}

}