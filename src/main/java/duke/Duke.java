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
        boolean isExit = false;
        while (!isExit) {
            try {
            //    isExit = this.ui.checkBye();
                this.ui.parseInput();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        if (isExit) {
            this.ui.sayBye();
        }
        storage.saveToFile(tasks);
    }

    public String getResponse(String input) {
        try {
            String output = this.ui.parseInput(input);
            storage.saveToFile(tasks);
            return output;
        } catch (DukeException e) {
            return ui.showError(e);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Starts Duke as it begins parsing the commands
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
