package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents Duke
 */
public class Duke {

    /** Default file path which Duke will attempt to access to load Task List */
    private static final String PATH_TO_FILE = "data/duke.txt";

    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs Duke using PATH_TO_FILE
     */
    public Duke() {
        storage = new Storage(PATH_TO_FILE);
        this.tasks = new TaskList();
    }

    public void loadTasks() throws DukeException {
        this.tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseFromUser(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
