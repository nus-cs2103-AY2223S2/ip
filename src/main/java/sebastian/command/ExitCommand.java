package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to exit the current session
 */
public class ExitCommand extends Command {
    /**
     * To exit the current session
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of task execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String res = "Take care, Ciel. It has been an honor serving you";
        return ui.getFormattedString(res);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
