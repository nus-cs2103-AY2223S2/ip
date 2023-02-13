package duke;

import duke.Command.Command;
import duke.Exception.DukeException;

/**
 * The main class to run duke.Duke
 * @author Zong Hao (ZHTang29)
 */

public class Duke {
    private TaskList tl;
    private Storage storage;
    private UI ui;
    private final String FILEPATH = "tasks.txt";
    private boolean isExit = false;

    /**
     * Constructor to set up duke.Duke, tries to load an existing list from the file.
     * If successful, the current task list will be loaded with the contents of the file.
     * If unsuccessful, create a new file with the file name, and new empty task list to add to.
     */
    public Duke() {
        ui = new UI();
        storage = new Storage(FILEPATH);

        try {
            tl = new TaskList(storage.loadFromFile());
        } catch (DukeException exception) {
            ui.showLoadingError();
            tl = new TaskList();
        }
    }

    /**
     * Parse a given user input and returns a String representing the result of the command execution.
     * If the user input is valid, returns the confirmation message/the current task list.
     * If the user input is not valid, returns the corresponding error message.
     * @param input The user input.
     * @return The response from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = new Parser().parseCommand(input);
            isExit = c.isByeCommand();
            return c.execute(tl, ui, storage);
        } catch (DukeException exception) {
            return exception.toString();
        }
    }

    public boolean isShutdownTime() {
        return isExit;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
    }
}
