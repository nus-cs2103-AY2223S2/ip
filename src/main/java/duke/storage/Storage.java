package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import duke.contact.Contact;
import duke.contact.ContactList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;



/**
 *  A file manager to act as storage for user's tasks.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Storage {
    private String path;

    /**
     * Constructor to create user's storage system.
     *
     * @param path relative filepath for user's tasks to be stored.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Saves the user's existing contacts into a .txt file.
     *
     * @param contactList List of contacts to be saved from the user.
     */

    public void saveContacts(ContactList contactList) {
        try {
            FileWriter fileWriter = new FileWriter(this.path);

            for (int j = 0; j < contactList.getCurrSize(); j ++) {
                Contact curr = contactList.getContacts().get(j);
                fileWriter.write("C/" + curr.getName() + "/" + curr.getPhoneNumber());
                if (j < contactList.getCurrSize() - 1) {
                    fileWriter.write("\n");
                }
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("jialat got problem bro\n" + exception.getMessage());
        }
    }
    /**
     * Saves user's existing tasks into a .txt file.
     *
     * @param tasklist List of tasks to be saved from the user.
     */

    public void saveTask(TaskList tasklist) {
        try {
            FileWriter fileWriter = new FileWriter(this.path);

            for (int i = 0; i < tasklist.getCurrentSize(); i++) {

                if (tasklist.getTasks().get(i) instanceof Deadline) {
                    Deadline curr = (Deadline) tasklist.getTasks().get(i);
                    fileWriter.write(curr.getType() + "/" + String.valueOf(curr.getCompletionStatus()
                            + "/" + curr.getName()));
                    fileWriter.write("/" + curr.getEndTime());
                } else if (tasklist.getTasks().get(i) instanceof Event) {
                    Event curr = (Event) tasklist.getTasks().get(i);
                    fileWriter.write(curr.getType() + "/" + String.valueOf(curr.getCompletionStatus())
                            + "/" + curr.getName());
                    fileWriter.write("/" + curr.getStartTime());
                    fileWriter.write("/" + curr.getEndTime());
                } else if (tasklist.getTasks().get(i) instanceof Todo) {
                    Todo curr = (Todo) tasklist.getTasks().get(i);
                    fileWriter.write(curr.getType() + "/" + String.valueOf(curr.getCompletionStatus())
                            + "/" + curr.getName());
                }
                if (i < tasklist.getCurrentSize() - 1) {
                    fileWriter.write("\n");
                }
            }

            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("jialat got problem bro\n" + exception.getMessage());
        }

    }

    /**
     * Loads an existing user's tasks into the tasklist.
     *
     * @return A list of tasks previously saved by the user.
     */
    public TaskList loadTask() {
        TaskList tasklist = new TaskList(100);
        try {
            File file = new File(this.path);
            if (!file.exists()) {
                Files.createFile(Path.of("./duke.txt"));
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String temp1 = sc.nextLine();
                String[] input = temp1.split("/");
                if (input[0].equals("T")) {
                    Todo temp = new Todo(input[2]);
                    if (input[1].equals("true")) {
                        temp.markAsDone();
                    }
                    tasklist.append(temp);
                } else if (input[0].equals("D")) {
                    Deadline temp = new Deadline(input[2], input[3]);
                    if (input[1].equals("true")) {
                        temp.markAsDone();
                    }
                    tasklist.append(temp);
                } else if (input[0].equals("E")) {
                    Event temp = new Event(input[2], input[3], input[4]);
                    if (input[1].equals("true")) {
                        temp.markAsDone();
                    }
                    tasklist.append(temp);
                } else {
                    System.out.println("Invalid type idek how u got here man");
                }
            }

        } catch (IOException exception) {
            System.out.println("bro where tf is ur file");
            System.out.println(exception.getMessage());
        }
        return tasklist;
    }

    /**
     * Loads an existing user's contacts into the contact list.
     *
     * @return The list of user's existing contacts.
     */
    public ContactList loadContacts() {
        ContactList contactList = new ContactList(100);
        try {
            File file = new File(this.path);
            if (!file.exists()) {
                Files.createFile(Path.of("./dukeContacts.txt"));
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String temp1 = sc.nextLine();
                String[] input = temp1.split("/");
                if (input[0].equals("C")) {
                    contactList.addContact(input[1], input[2]);
                }
            }
        } catch (IOException exception) {
            System.out.println("bro where tf is ur file");
            System.out.println(exception.getMessage());
        }
        return contactList;
    }


}
