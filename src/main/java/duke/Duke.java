package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents Duke
 */
public class Duke {

    /** Default file path which Duke will attempt to access to load duke.task.Task List */
    private static final String PATH_TO_FILE = "data/duke.txt";

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs Duke using specified filePath
     * @param filePath path to file where Duke will read from and save to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseFromUser(fullCommand);
                c.execute(tasks, ui, storage);
                ui.showLine();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodBye();
    }

    public static void main(String[] args) {
        new Duke(PATH_TO_FILE).run();
    }


}
