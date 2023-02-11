package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * The main class for running the application
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the duke.Duke object
     * @param filePath A string representing the path of the file containing all tasks
     * @param folderPath A string representing the path of the folder containing the above file
     */
    public Duke(String filePath, String folderPath) {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }
    /** gets the output message from a Command */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
