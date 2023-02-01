package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

public class FindTaskCommand extends Command {
    public FindTaskCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.findTask(inputArr);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
