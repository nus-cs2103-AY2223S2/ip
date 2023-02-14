package duke;

import java.nio.file.Path;

import command.Command;
import duke.controllers.MainWindow;
import task.TaskList;

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
            assert taskList != null : "taskList should not be null.";
        } catch (DukeException e) {
            this.app.addDukeDialog(e.getMessage());
            this.taskList = new TaskList();
        } finally {
            this.ui.showWelcome();
        }
    }

    /**
     * Comprehends the user text input and execute the relevant action and producing a response
     * corresponding to the input.
     * @param input The user text input.
     */
    public void handleUserInput(String input) {
        boolean isExit = false;

        try {
            this.ui.showUserInput(input);
            Command c = Parser.parseUserCommand(input);
            String response = c.execute(this.taskList);
            this.ui.showSuccess(response);
            isExit = c.isExit();
            if (isExit) {
                this.storage.save(this.taskList); /* save tasks at the end of the chat session */
            }
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
        } finally {
            if (isExit) {
                this.app.terminate();
            }
        }
    }
}
