package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

public class ListCommand extends Command {
    public ListCommand(String[] inputArr) {
        super(inputArr);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.listTask(inputArr);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
