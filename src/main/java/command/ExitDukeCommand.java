package command;

import store.Storage;
import store.TaskList;
import userinteraction.Ui;

public class ExitDukeCommand extends Command {
    public ExitDukeCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
