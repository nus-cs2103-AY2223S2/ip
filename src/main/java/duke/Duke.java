package duke;

import duke.command.Command;

import duke.exception.DukeException;

/** Class that encapsulates the Duke chatbot */
public class Duke {

    /** Storage object to interact with storage */
    private Storage storage;
    /** TaskList object to store tasks */
    private TaskList tasks;
    /** UI object to display user interface and read user input */
    private Ui ui;

    private boolean isExit = false;

    /**
     * Constructs a Duke object with a specified path
     * to the data directory used for storing tasks.
     *
     * @param dirPath Relative path to the data directory
     *                used for storing tasks.
    */
    public Duke(String dirPath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showErrorMessage(e);
            this.tasks = new TaskList();
        }
    }

    public String runCommand(String command) {
        try {
            Command c = Parser.parseCommand(command);
            this.isExit = c.isExit();
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return this.ui.showErrorMessage(e);
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

}
