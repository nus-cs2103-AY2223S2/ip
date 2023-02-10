package duke.contact;



import java.util.ArrayList;

public class ContactList {

    private ArrayList<Contact> contacts;

    /**
     * Creates a Contact List object ot hold the list of contacts
     *
     * @param size Maximum number of elements in the contact list
     */
    public ContactList(int size) {
        this.contacts = new ArrayList<>(size);
    }

    public int getCurrSize() {
        return contacts.size();
    }

    public ArrayList<Contact> getContacts() {
        return this.contacts;
    }

    /**
     * Creates a new contact object and adds it into the contact list.
     *
     * @param name
     * @param phoneNumber
     * @return Output message to display
     */
    public String addContact(String name, String phoneNumber) {
        Contact temp = new Contact(name, phoneNumber);
        contacts.add(temp);
        String msg = "gratz bro this fella " + temp.toString() + " is your new buddy";
        return msg;
    }

    /**
     * Deletes an existing contact from your list of contacts
     *
     * @param command input specifying the index of the contact to be deleted
     * @return Output message to display
     */
    public String deleteContact(String[] command) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        try {
            Contact temp = contacts.get(taskPointer);
            contacts.remove(taskPointer);
            String msg = "";
            msg += "Ok bro I've removed this : \n";
            msg += String.format(temp.toString()
                    + "\nfrom the list of your friends \n");
            msg += String.format("Hope you have a better life now without them \n");
            return msg;
        } catch (IndexOutOfBoundsException e) {
            return "brother look at how long ur list is first then delete lah\n";
        }
    }

    /**
     * Method to list out all the contacts the user has.
     *
     */
    public String listContacts() {
        String str = "";
        if (this.contacts.size() == 0) {
            str += "You got no friends bro what can u do abt this\n";
        }
        for (int i = 0; i < this.contacts.size(); i++) {
            if (this.contacts.get(i) == null) {
                break;
            }
            String str2 = String.format("%d." + contacts.get(i).toString() + "\n", i + 1);
            str += str2;
        }
        return str;
    }

}
