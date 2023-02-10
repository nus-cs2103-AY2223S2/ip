package duke.contact;

public class Contact {

    private String name;

    private String phoneNumber;

    /**
     * Constructor to create a contact object.
     *
     * @param name Name of the person you are adding to contact.
     * @param phoneNumber Phone number of the person you are adding to contacts.
     */
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    /**
     * Converts a contact to its String representation.
     *
     * @return The string representation of the contact object to be added
     */
    @Override
    public String toString() {
        String str = "[C] " + this.name + " " + this.phoneNumber;
        return str;
    }
}
