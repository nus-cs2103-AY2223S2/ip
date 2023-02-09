package duke;

import java.util.ArrayList;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents Duke
 */
public class Duke {

    /** Default file path which Duke will attempt to access to load Task List */
    private static final String PATH_TO_FILE = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs Duke using PATH_TO_FILE
     */
    public Duke() {
        this.storage = new Storage(PATH_TO_FILE);
        this.tasks = new TaskList();
    }

    /**
     * Calls storage to load tasks from file, then sets loaded list as task list
     * @throws DukeException if failed to load
     */
    public void loadTasks() throws DukeException {
        ArrayList<Task> list = storage.load();
        this.tasks.setList(list);
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
