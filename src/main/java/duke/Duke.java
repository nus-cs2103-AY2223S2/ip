package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.EmptyStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The main class that represents the chatbot, Duke, which helps
 * to build a checklist consisting of todos, deadlines, events.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath For Duke to find the path to an existing data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (EmptyStorageException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * The function that handles the parsing, storage
     * and ui of Duke. Also handles the exceptions coming
     * from Command.
     */
    public void run() {
        this.ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method. Initialises a Duke instance and
     * starts up the cogwheel.
     *
     * @param args Takes in the command line argument.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();

    }


}
