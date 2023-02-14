package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Entry point of the application.
 * Initialize the program and interact with the user.
 */
public class Duke {
    protected String greetingMsg;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {}

    /**
     * Constructor to create duke instance.
     *
     * @param filePath description of the file path.
     */
    public Duke(String filePath) {
        assert filePath != null : "Empty filepath";

        ui = new Ui();
        setGreetingMsg();

        try {
            storage = new Storage(filePath);
            setTasks(new TaskList(storage.load()));
        } catch (DukeException e) {
            Storage.setDefaultStorage();
            setTasks(new TaskList());
        }
    }

    public void setTasks(TaskList taskList) {
        tasks = taskList;
    }

    public void setGreetingMsg() {
        greetingMsg = ui.showWelcomeMessage();
    }

    public String getGreetingMsg() {
        return greetingMsg;
    }

    /**
     * Return response given a user input.
     *
     * @param input command to execute.
     * @return response given by duke
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim());
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}