package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.uitext.UiText;
import javafx.application.Platform;

/**
 * Represents a Duke chatbot that stores a tasklist.
 */
public class Duke {

    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static Storage storage;
    private static UiText uiText = new UiText();

    /**
     * Constructor. Required for JavaFX.
     */
    public Duke() {

    }

    /**
     * Constructor.
     * @param filePath Path of the file where task data is stored.
     */
    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        taskList = storage.load(parser);
    }

    private String acceptOneCommand(String input) {
        String output = "";
        try {
            Command command = parser.parseCommand(input);
            if (command.isExit()) {
                Platform.exit();
            } else {
                output = command.execute(taskList, uiText, storage);
            }
            storage.save(taskList);
            return output;
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Returns a string in response to the given user input, which is a command.
     * @param input The user input.
     * @return A response to the given user input.
     */
    public String getResponse(String input) {
        return acceptOneCommand(input);
    }

}
