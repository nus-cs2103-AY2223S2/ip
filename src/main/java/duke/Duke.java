package duke;

import duke.command.Command;
import duke.exceptions.DukeException;

public class Duke {
    private static final String SAVE_PATH = "./tasklist.txt";

    private final Storage storage;
    private TaskList tasks;

    public Duke() {
        this.storage = new Storage(SAVE_PATH);
        try {
            this.tasks = new TaskList(this.storage.loadTasklistFromFile());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns a response to a command given by the user.
     *
     * @param input Command given by the user.
     * @return The output of running the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this.tasks, this.storage);
            return c.getOutput();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
