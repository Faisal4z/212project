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

public class Contact implements Comparable<Contact> {
	private String name;
	private String phone;
	private String email;
	private String address;
	private String birthday;
	private String notes;
	
	public Contact(String name, String phone, String email, String address, String birthday, String notes) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getNotes() {
		return notes;
	}

	public void Display() {
		System.out.println("");
		System.out.println("Name: " + name);
		System.out.println("Phone Number: " + phone);
		System.out.println("Email Address: " + email);
		System.out.println("Address: " + address);
		System.out.println("Birthday: " + birthday);
		System.out.println("Notes: " + notes);
		System.out.println("");
		// bigO(1)
	}

	@Override
	public int compareTo(Contact o) {
		String te = o.getName().toLowerCase();

		return this.getName().compareTo(te);
	}

}
