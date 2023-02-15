package duke;

import duke.backend.DukeCommand;
import duke.backend.Parser;
import duke.backend.Storage;
import duke.backend.TaskList;
import duke.exception.DukeException;
import javafx.fxml.FXML;

/**
 * The main class of the Duke program.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;

    /**
     * The constructor of the Duke class.
     */
    public Duke() {
        storage = new Storage("data/duke.txt");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response of Duke to the input.
     *
     * @param input The input given by the user.
     * @return The response of Duke to the input.
     */
    @FXML
    public String getResponse(String input) {
        DukeCommand command = Parser.parseInput(input);
        return command.execute(tasks, storage);
    }
}
