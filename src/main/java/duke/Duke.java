package duke;

import static duke.util.Ui.getUi;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;

/**
 * The Duke class connects all the components to form the main chatbot logic.
 * @author Junyi
 */
public class Duke {

    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructor for Duke chatbot.
     * @param filePath Path for data storage file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            getUi().showLoadingError();
            tasks = new TaskList();
        }
        taskList = tasks;

        parser = new Parser(taskList, storage);
    }


    /**
     * Returns a response from the user input command.
     * @param cmd The user input command.
     * @return A string to be displayed to the user.
     */
    public String getResponse(String cmd) {
        assert cmd.length() <= 0 : "Command should not be an empty string";

        try {
            Command command = parser.parseUserCommand(cmd);
            return command.execute();
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
