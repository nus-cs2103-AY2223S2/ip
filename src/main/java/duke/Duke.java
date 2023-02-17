package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Duke's main class
 */
public class Duke {

    private static TaskList tasks;
    private Ui ui;
    private final String filePath = "duke.txt";
    private Storage storage;

    /**
     * Constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
        ui.displayTaskList(tasks);
    }

    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            return c.initCommand(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
