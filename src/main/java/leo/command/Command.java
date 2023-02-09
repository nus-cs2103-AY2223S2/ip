package leo.command;

import leo.leoexception.LeoException;
import leo.storage.Storage;

/**
 * Represents a command input by user.
 */
public class Command {

    public final Storage storage;
    public final String command;

    /**
     * Constructor to create a Command object.
     *
     * @param s Storage to store the task.
     * @param command Description of the task.
     */
    public Command(Storage s, String command) {
        this.storage = s;
        this.command = command;
    }

    public String execute() throws LeoException {
        return "";
    }

    /**
     * Updates and write tasks to data file.
     * Prints farewell message and exits program.
     */
    public String exit() {
        try {
            storage.writeToFile();
            return "Good bye, have a nice day ahead!";
        } catch (LeoException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns index of a task in the list.
     *
     * @return Index of task.
     */
    public int extractTaskNum() {
        String num = command.replaceAll("[^0-9]", "");
        return Integer.parseInt(num);
    }
}
