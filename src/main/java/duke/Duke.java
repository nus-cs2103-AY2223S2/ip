package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * The Duke class connects all the components to form the main chatbot logic.
 * @author Junyi
 */
public class Duke {

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Duke chatbot.
     * @param filePath Path for data storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        taskList = tasks;

        parser = new Parser(taskList, storage, ui);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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
