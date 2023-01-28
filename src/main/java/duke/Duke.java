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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // Initialize the task list
        this.list = new TaskList();
        // Initialize the user interface
        this.ui = new Ui();
        // Initialize the storage object
        this.storage = new Storage(filePath);
        // Initialize the command history
        this.commandHistory = new CommandHistory();

        try {
            // Attempt to load tasks from storage
            this.list = this.storage.load();
        } catch (InvalidInputException e) {
            // Handle invalid input exception
            this.ui.displayWithBar(e.getMessage());
            this.list = new TaskList();
        } catch (StorageFileException e) {
            // Handle storage file exception
            this.ui.displayWithBar(e.getMessage());
            // Throw a runtime exception
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
            // reset the ui
            this.ui.reset();
            // parse the input and create a command
            Command command = Parser.parse(input);
            // execute the command
            command.execute(this.list, this.ui, this.storage, this.commandHistory);
            // return the response from the ui
            return this.ui.getResponse();
        } catch (DukeException e) {
            // append the error message to the ui's response
            this.ui.appendResponse(e.getMessage());
            return this.ui.getResponse();
        }
    }
}
