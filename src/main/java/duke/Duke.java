package duke;

import duke.commands.ICommand;
import duke.exceptions.DukeException;
import duke.utilities.TaskManager;
import duke.utilities.UserInterface;

/**
 * Main class represents Duke chatbot
 */
public class Duke {
    private TaskManager taskManger;
    private final UserInterface ui;

    /**
     * Constructor for Duke
     *
     * @param filePath the path to save and load the tasks from disk
     */
    public Duke(String filePath) {
        ui = new UserInterface();
        try {
            taskManger = new TaskManager(filePath);
            taskManger.load();
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            taskManger = new TaskManager(filePath);
        }
    }

    /**
     * Runs and starts the Duke bot
     */
    public void run() {
        ui.showWelcome();
        boolean isDone = false;
        while (!isDone) {
            try {
                ICommand cmd = ui.readCommand(taskManger);
                isDone = cmd.run();
                ui.speak(cmd.getMsg());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.getCommand("Enter new command to continue: ");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.tasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            assert !input.isEmpty() : "Please type something!";
            ICommand cmd = ui.readCommand(taskManger, input);
            cmd.run();
            return cmd.getMsg();
        } catch (DukeException e) {
            return e.getMessage();
        }

    }
}

