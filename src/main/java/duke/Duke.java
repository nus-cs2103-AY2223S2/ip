package duke;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This class is the main of the program that allows user to track upcoming tasks
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs new instance of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
