package duke;

import support.Storage;

import task.TaskList;

import drivers.Parser;
/**
 * Contains main method and starts the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initialise objects needed for the program.
     */
    public Duke() {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    /**
     * Starts program and stores data into log after done.
     */
    public void run() {
        this.storage.update(tasks.print());
    }

    public String getResponse(String input) {
        return this.parser.readCommand(input, this.tasks);
    }
}
