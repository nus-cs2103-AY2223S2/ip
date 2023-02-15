package nemo;

import nemo.command.Command;
import nemo.exception.NemoException;
import nemo.storage.Storage;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Main class to run Nemo programme.
 *
 * @author Lian Kok Hai
 *
 */
public class Nemo {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Nemo instance with saved information loaded from save directory.
     */
    public Nemo() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser();
        try {
            storage.loadFromSave(taskList);
        } catch (NemoException e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Get Nemo's response.
     * @param input String input by user.
     * @return Nemo's response.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (NemoException e) {
            return ui.getErrorMessage() + "\n" + e.getMessage();
        }
    }

    /**
     * Get welcome message.
     *
     * @return Welcome message
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }
}
