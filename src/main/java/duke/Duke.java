package duke;

import duke.command.Command;

import duke.exception.DukeException;

/** Class that encapsulates the Duke chatbot */
public class Duke {

    /** Relative path to the data directory used for storing tasks */
    private static final String dirPath = "./data/";
    /** Storage object to interact with storage */
    private Storage storage;
    /** TaskList object to store tasks */
    private TaskList tasks;
    /** UI object to display user interface and read user input */
    private Ui ui;

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

    /**
     * Runs the main loop of the Duke chatbot
     * where Duke takes in user input
     * and responds to the commands given.
     */
    public void run() {
        this.ui.showGreeting();
        this.ui.showSeparator();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showArrow();
                String fullCommand = this.ui.readCommand();
                ui.showSeparator();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showSeparator();
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Duke(Duke.dirPath).run();
    }

}
