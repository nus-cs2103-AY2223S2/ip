package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.StorageFileException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main Duke class to run
 */
public class Duke {
    private final Storage storage;
    private TaskList list;
    private final Ui ui;

    /**
     * The constructor that takes in a String filePath that specifies the path for the storage file.
     *
     * @param filePath Specifies the path for the storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        list = new TaskList();

        try {
            list = storage.load();
        } catch (InvalidInputException e) {
            ui.displayWithBar(e.getMessage());
            list = new TaskList();
        } catch (StorageFileException e) {
            ui.displayWithBar(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the response from Duke
     *
     * @param input User input
     * @return Response from Duke
     */
    public String getResponse(String input) {
        try {
            ui.reset();
            Command c = Parser.parse(input);
            c.execute(list, ui, storage);
            return ui.getResponse();
        } catch (DukeException e) {
            ui.appendResponse(e.getMessage());
            return ui.getResponse();
        }
    }
}
