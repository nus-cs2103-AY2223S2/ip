package seedu.duke;

/**
 * Represents the Duke bot.
 * The Duke bot as a tasklist, a storage, a user interface and a parser.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = this.storage.read();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public String spawn() {
        return ui.spawnBot();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException {
        return parser.parse(taskList, storage, ui, input);
    }
}



