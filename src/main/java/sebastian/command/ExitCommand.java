package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

public class ExitCommand extends Command{
    /**
     * To exit the current session
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String res = "Bye. It's my pleasure to serve you";
        ui.printFormattedString(res);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
