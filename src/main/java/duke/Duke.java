package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

/**
 * Represents the Duke chatbot.
 * @author lukkesreysandeur
 */
public class Duke {
    /** The Ui object that handles interactions with the user. */
    private final Ui ui;
    /** The list that contains the actual task objects. */
    private final TaskList tasks;
    /** The storage object that helps to save state to a file. */
    private final Storage storage;
    /** The parser object that helps to make sense of the commands entered by the user. */
    private final Parser parser;

    /**
     * Initialises the Duke object by initialising all required components and loading the save file in.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser();
        try {
            storage.loadState(tasks);
        } catch (DukeException e) {
            System.out.println(ui.showError(e));
        }
    }

    /**
     * Runs the Duke chatbot.
     *
     * @return The welcome message.
     */
    public String run() {
        return ui.welcomeMessage();
    }

    /**
     * Parses the input into a command and returns the result to the GUI.
     *
     * @param input The user input.
     * @return A string with the chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
