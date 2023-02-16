package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main logic of Duke the chat-bot which tracks the user's tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Get duke response after receiving input from the User.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (IllegalArgumentException e) {
            return "Please give an actual command. Read the user guide for more information";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
