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

    /** Default file path which Duke will attempt to access to load Task List */
    private static final String PATH_TO_FILE = "data/duke.txt";

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;



    /**
     * Constructs Duke using PATH_TO_FILE
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(PATH_TO_FILE);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseFromUser(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }

}
