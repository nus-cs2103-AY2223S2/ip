package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.StorageFileException;
import duke.parser.Parser;
import duke.storage.CommandHistory;
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
    private final CommandHistory commandHistory;

    /**
     * The constructor that takes in a String filePath that specifies the path for the storage file.
     *
     * @param filePath Specifies the path for the storage file
     */
    public Duke(String filePath) {
        this.list = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.commandHistory = new CommandHistory();

        try {
            this.list = this.storage.load();
        } catch (InvalidInputException e) {
            this.ui.displayWithBar(e.getMessage());
            this.list = new TaskList();
        } catch (StorageFileException e) {
            this.ui.displayWithBar(e.getMessage());
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
            this.ui.reset();
            Command command = Parser.parse(input);
            command.execute(this.list, this.ui, this.storage, this.commandHistory);
            return this.ui.getResponse();
        } catch (DukeException e) {
            this.ui.appendResponse(e.getMessage());
            return this.ui.getResponse();
        }
    }
}
