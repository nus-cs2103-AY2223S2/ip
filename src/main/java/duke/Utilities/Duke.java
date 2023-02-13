package duke.Utilities;

import duke.Command.Command;
import duke.Exception.DukeException;

/**
 * The main class to run duke.Utilities.Duke
 * @author Zong Hao (ZHTang29)
 */

public class Duke {
    private TaskList tl;
    private NoteList nl;
    private Storage storage;
    private UI ui;
    private final String FILEPATH = "data.txt";
    private boolean isExit = false;

    /**
     * Constructor to set up Duke, tries to load existing lists from the file.
     * If successful, the current task & note list will be loaded with the contents of the file.
     * If unsuccessful, create a new file with the file name, and new empty task & note list to add to.
     */
    public Duke() {
        ui = new UI();
        storage = new Storage(FILEPATH);

        try {
            tl = new TaskList(storage.loadTasksFromFile());
            nl = new NoteList(storage.loadNotesFromFile());
        } catch (DukeException exception) {
            ui.showLoadingError();
            tl = new TaskList();
            nl = new NoteList();
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
            return c.execute(tl, nl, ui, storage);
        } catch (DukeException exception) {
            return exception.toString();
        }
    }

    /**
     * Check if it is time to close the app (based on whether the user has inputted the 'bye' command).
     * @return True if 'bye' command has been entered, false otherwise.
     */
    public boolean isShutdownTime() {
        return isExit;
    }
}
