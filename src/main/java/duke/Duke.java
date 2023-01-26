
package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import java.io.IOException;

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

    public static void main(String[] args) {
        new Duke().run();
    }
}
