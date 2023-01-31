package leo.command;

import leo.leoexception.LeoException;
import leo.storage.Storage;
import leo.ui.Ui;

/**
 * Represents a command input by user.
 */
public class Command {

    private final Storage storage;
    private final String command;

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

    /**
     * Returns index of a task in the list.
     *
     * @return Index of task.
     */
    public int extractTaskNum() {
        String num = command.replaceAll("[^0-9]", "");
        return Integer.parseInt(num);
    }

    /**
     * Updates and write tasks to data file.
     * Prints farewell message and exits program.
     */
    public void exit() {
        try {
            storage.writeToFile();
            Ui.displayMessage(Ui.leoResponse("Good bye, have a nice day ahead!"));
            System.exit(0);
        } catch (LeoException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

}
