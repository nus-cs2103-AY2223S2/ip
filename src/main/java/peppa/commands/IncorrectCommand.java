package peppa.commands;

import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

public class IncorrectCommand implements Command {
    public IncorrectCommand() {}

    @Override
    public void execute(TaskList taskList, Ui screen, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
