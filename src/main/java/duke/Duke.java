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
    public Duke() throws DukeException {
        Storage.logFileExists(FILE_PATH);
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadTasksFromTaskLog());
    }

    /**
     * Runs Duke interface which interprets user input
     */
    public void run() throws DukeException {
        ui.greetUser();
        while (!isExit) {
            String userInput = ui.getInput();
            Command c = Parser.getCommandType(userInput);
            c.execute(tasks, ui);
            isExit = c.getExitStatus();
        }
    }

    /**
     * Initialise Duke chatbot
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
