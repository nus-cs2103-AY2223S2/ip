package duke;

/**
 * Main class
 */
public class Duke {

    /**
     * All logic for duke to handle user input
     * @throws DukeExceptions
     */
    public static void dukeHandleInput(String input) throws DukeExceptions {
        Ui.intro();
        Storage.createDataDir();
        TaskList tasks = new TaskList(Storage.load());
        tasks.handleInput(input);
    }
}
