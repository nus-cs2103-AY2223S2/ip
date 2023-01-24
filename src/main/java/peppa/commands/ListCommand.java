package peppa.commands;

import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

public class ListCommand implements Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui screen, Storage storage) {
        Ui.displayTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
