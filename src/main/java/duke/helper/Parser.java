package duke.helper;

import java.time.format.DateTimeParseException;

import duke.contact.ContactList;
import duke.exceptions.InvalidTaskDescriptionException;
import duke.tasks.TaskList;




/**
 * Helper Class to parse and execute commands from user.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Parser {

    /**
     * Function that parses through user input and executes associated command.
     *
     * @param input Raw string containing user input.
     * @param command Partially broken down input to make sense of commands.
     * @param tasks The list of tasks from the user.
     * @return String that denotes message to be displayed by ChadGPT
     */
    public static String run(String input, String[] command, TaskList tasks, ContactList contacts) {
        try {
            if (command[0].equals("list")) {
                return tasks.listTasks();
            } else if (command[0].equals("mark")) {
                return tasks.mark(command);
            } else if (command[0].equals("unmark")) {
                return tasks.unmark(command);
            } else if (command[0].equals("todo")) {
                return tasks.addTask(input);
            } else if (command[0].equals("deadline")) {
                return tasks.addDeadline(input);
            } else if (command[0].equals("event")) {
                return tasks.addEvent(input);
            } else if (command[0].equals("delete")) {
                return tasks.deleteTask(command);
            } else if (command[0].equals("find")) {
                return tasks.findTask(command[1]);
            } else if (command[0].equals("friend")) {
                return contacts.addContact(command[1], command[2]);
            } else if (command[0].equals("unfriend")) {
                return contacts.deleteContact(command);
            } else if (command[0].equals("listfriends")) {
                return contacts.listContacts();
            } else {
                return "Invalid command wake up brother";
            }
        } catch (InvalidTaskDescriptionException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException f) {
            return "bro wake up and type a proper command";
        } catch (DateTimeParseException d) {
            return "bro can u wake up and press the date properly";
        }

    }
}

