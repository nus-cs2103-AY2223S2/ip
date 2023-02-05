package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke class represents an instance of a Duke.
 *
 * @author Tan Wei Shwin, Linus
 * @version 0.1
 */
public class Duke {
    /** Storage of Duke */
    private Storage storage;
    /** TaskList of Duke */
    private TaskList tasks;
    /** Ui of Duke */
    private Ui ui;

    public Duke() {
        this("./data/tasks.ser");
    }

    /**
     * Constructs Duke class.
     *
     * @param filePath Filepath of TaskList.
     */
    public Duke(String filePath) {
        ui = new Ui();
//        ui.showStartUp();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.displayOutput();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return ui.displayOutput();
        }
    }
}
