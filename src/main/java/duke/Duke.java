package duke;

import drivers.Parser;
import support.Storage;
import task.TaskList;

/**
 * Contains main method and starts the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor used to initialise objects used.
     */
    public Duke() {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.storage.updateTasks(this.tasks);
        this.parser = new Parser();
    }

    public String getResponse(String input) {
        String str = this.parser.readCommand(input, this.tasks);
        this.storage.update(tasks.print());
        return str;
    }
}
