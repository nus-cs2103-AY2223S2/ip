package angela;

import angela.commands.Command;
import angela.exception.AngelaException;
import angela.parser.Parser;
import angela.storage.Storage;
import angela.task.TaskList;
import angela.uitext.UiText;
import javafx.application.Platform;

/**
 * Represents an Angela chatbot that stores a task list.
 */
public class Angela {

    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static Storage storage;
    private static UiText uiText = new UiText();

    /**
     * Constructor. Required for JavaFX.
     */
    public Angela() {

    }

    /**
     * Constructor.
     * @param filePath Path of the file where task data is stored.
     */
    public Angela(String filePath) throws AngelaException {
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
        } catch (AngelaException e) {
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
