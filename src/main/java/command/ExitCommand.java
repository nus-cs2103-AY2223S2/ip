package command;

import main.Storage;
import main.TaskList;
import main.Ui;

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
