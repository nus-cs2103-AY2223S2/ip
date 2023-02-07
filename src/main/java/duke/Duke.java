package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke helps user to keep track of tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser(tasks);
    }

    /**
     * Greet user and perform command for the tasks they entered and save tasks in file
     */
    public void run() {
        ui.greet();
        parser.performCommand();
        storage.save(this.tasks);
    }


    public static void main(String[] args) {
        new Duke().run();

    }
}
