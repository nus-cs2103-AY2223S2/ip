package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;

/**
 * Class for Duke, a Personal Assistant Chatbot
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String FILE_PATH = ("./data/Duke.txt");
    private static boolean isExit = false;

    /**
     * Constructor for Duke Class. If log file does not exist, create a new log file
     */
    public Duke() {
        Storage.logFileExists(FILE_PATH);
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadTasksFromTaskLog());
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.getCommandType(input);
            return c.execute(tasks);
        } catch (DukeException e) {
            return ("Invalid input");
        }
    }

    public String showWelcomeMessage() {
        return "Hello";
    }
}