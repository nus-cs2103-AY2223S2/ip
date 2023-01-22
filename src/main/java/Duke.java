import commands.*;
import exceptions.DukeException;
import utilities.UserInterface;
import utilities.TaskManager;


public class Duke {
    private TaskManager _taskManger;
    private final UserInterface _ui;

    public Duke(String filePath) {
        _ui = new UserInterface();
        try {
            _taskManger = new TaskManager(filePath);
        } catch (DukeException e) {
            _ui.showLoadingError(e.getMessage());
            _taskManger = new TaskManager();
        }
    }

    public void run() {
        _ui.showWelcome();
        boolean isDone = false;
        while (!isDone) {
            try {
                ICommand cmd  = _ui.readCommand(_taskManger);
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
        new Duke("data/tasks.txt").run();
    }
}
