package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the Duke personal assistant.
 * @author pzhengze
 */
public class Duke {
    /** Reference for the Storage object used for i/o to save file. */
    private final Storage storage;
    /** Reference for the TaskList object used for storing Task objects. */
    private TaskList tasks;
    /** Reference for the Ui object used for user interaction in the console. */
    private final Ui ui;
    /** Reference for the Parser object used for parsing user input. */
    private final Parser parser;
    /** Relative path to the save file. */
    private static final String PATH = "data/tasks.txt";

    /**
     * Constructor for Duke object.
     * Creates a new Object with the save file at the specified path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(PATH);
        try {
            tasks = new TaskList(storage.loadSaveFile());
        } catch (DukeException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    /**
     * Creates a new Duke object at the default save path and runs it.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the duke object, allowing the user to interact with it.
     */
    public void run() {
        this.ui.greet();
        String input = "";
        do {
            try {
                input = this.ui.getUserInput();
                this.ui.printResponse(parser.parseAndExecute(input));
            } catch (DukeException e) {
                this.ui.printException(e);
            }
        } while (!input.equals("bye"));

        try {
            this.storage.save(tasks.getTasks());
        } catch (DukeException e) {
            this.ui.printException(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return this.parser.parseAndExecute((input));
        } catch (DukeException e) {
            return "Sorry Duke failed to understand";
        }
    }
}

