package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Main class for Fake Duke the chat bot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke class.
     *
     * @param filePath of the local file for tasks storage.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage() + " Will be creating a new task list instead~\n");
            tasks = new TaskList();
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }
}
