package duke;

import command.Command;
import duke.controllers.MainWindow;
import task.TaskList;

import java.nio.file.Path;

/**
 * The Duke ChatBot.
 */
public class Duke {
    private MainWindow app;
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     * @param app The GUI instance.
     * @param filePath The path of the local file where tasks are stored from the previous session.
     */
    public Duke(MainWindow app, Path filePath) {
        this.app = app;
        this.ui = new Ui(app);
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
            this.ui.showWelcome();
        } catch (DukeException e) {
            this.app.addDukeDialog(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Handles the user text input by parsing it, executing the relevant command and
     * returning the text response by Duke.
     * @param input The user text input.
     */
    public void handleUserInput(String input) {
        try {
            this.ui.showUserInput(input);
            Command c = Parser.parse(input);
            String response = c.execute(this.taskList);
            this.ui.showSuccess(response);
            if (c.isExit()) {
                this.storage.save(this.taskList); /* save tasks at the end of the chat session */
                this.app.terminate();
            }
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
        }
    }
}
