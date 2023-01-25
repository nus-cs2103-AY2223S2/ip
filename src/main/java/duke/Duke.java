package duke;

import drivers.Ui;
import support.Storage;
import task.TaskList;

/**
 * Contains main method and starts the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialise objects needed for the program.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
    }

    /**
     * Starts program and stores data into log after done.
     */
    public void run() {
        this.ui.nextMission(this.tasks);
        this.storage.update(tasks.print());
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
