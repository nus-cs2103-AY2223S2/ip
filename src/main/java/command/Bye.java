package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Encapsulates bye command
 */
public class Bye implements Command {
    public Bye() {

    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit() {
        return true;
    }
}
