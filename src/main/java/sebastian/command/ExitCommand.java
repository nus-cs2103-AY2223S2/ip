package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

public class ExitCommand extends Command{
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
