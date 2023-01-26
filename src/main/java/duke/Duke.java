
package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * Represents a build instance of Duke.
 * A Duke instance is created when Duke is run.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.initData());
        } catch (IOException e) {
            tasks = new TaskList();
        }
        ui = new Ui(tasks);
    }

    /**
     * Duke's program flow
     */
    public void run() {
        this.ui.sayHi();
        boolean exit = false;
        while (!exit) {
            try {
                exit = this.ui.parseInput();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        if (exit) {
            this.ui.sayBye();
        }
        storage.saveToFile(tasks);
        //...
    }

    /**
     * Starts Duke as it begins parsing the commands
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
