package duke;

import duke.command.Command;

/**
 * Duke is a personal assistant chatbot that helps a person to keep track of various things.
 * 
 * @author Tan Yu Fei
 * @version 0.1
 * @since 2022-01-25
 */
public class Duke {
    /** The storage object that handles the loading and saving of tasks. */
    private Storage storage;
    /** The task list that contains the tasks. */
    private TaskList tasks;
    /** The ui object that handles the user interface. */
    private Ui ui;

    /**
     * Constructor for Duke.
     * 
     * @param filePath the path of the file that contains the tasks.
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            ui = new Ui();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets the response from Duke.
     * 
     * @param input the input from the user.
     * @return the response from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the welcome message from Duke.
     */
    public String getWelcome() {
        return ui.getWelcome();
    }
}
