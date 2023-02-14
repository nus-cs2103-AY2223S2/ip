package wtd;

import wtd.command.Command;
import wtd.exceptions.WtdException;
import wtd.exceptions.StorageException;

/**
 * Wtd is a personal assistant chatbot that helps a person to keep track of various things.
 * 
 * @author Tan Yu Fei
 * @version 0.1
 * @since 2022-01-25
 */
public class Wtd {
    /** The storage object that handles the loading and saving of tasks. */
    private Storage storage;
    /** The task list that contains the tasks. */
    private TaskList tasks;
    /** The ui object that handles the user interface. */
    private Ui ui;

    /**
     * Constructor for Wtd.
     * 
     * @param filePath the path of the file that contains the tasks.
     */
    public Wtd(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            ui = new Ui();
        } catch (StorageException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Gets the response from Wtd.
     * 
     * @param input the input from the user.
     * @return the response from Wtd.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (WtdException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the welcome message from Wtd.
     */
    public String getWelcome() {
        return ui.getWelcome();
    }
}
