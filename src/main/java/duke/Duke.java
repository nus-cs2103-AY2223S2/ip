package duke;

import duke.commands.ICommand;
import duke.exceptions.DukeException;
import duke.utilities.TaskManager;
import duke.utilities.UserInterface;

/**
 * Main class represents Duke chat-bot
 */
public class Duke {
    private TaskManager _taskManger;
    private final UserInterface _ui;

    /**
     * Constructor for Duke
     *
     * @param filePath the path to save and load the tasks from disk
     */
    public Duke(String filePath) {
        _ui = new UserInterface();
        try {
            _taskManger = new TaskManager(filePath);
            _taskManger.load();
        } catch (DukeException e) {
            _ui.showLoadingError(e.getMessage());
            _taskManger = new TaskManager(filePath);
        }
    }

    /**
     * run and start the Duke bot
     */
    public void run() {
        _ui.showWelcome();
        boolean isDone = false;
        while (!isDone) {
            try {
                ICommand cmd = _ui.readCommand(_taskManger);
                isDone = cmd.run();
                _ui.Speak(cmd.getMsg());
            } catch (DukeException e) {
                _ui.showError(e.getMessage());
            } finally {
                _ui.getCommand("Enter new command to continue: ");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.tasks.txt").run();
    }
}

