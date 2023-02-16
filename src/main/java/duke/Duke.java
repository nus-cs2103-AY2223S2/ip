package duke;

import duke.command.Command;

public class Duke {
    private static final String SAVE_PATH = "./tasklist.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(SAVE_PATH);
        try {
            this.tasks = new TaskList(this.storage.loadTasklistFromFile());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return c.getOutput();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
