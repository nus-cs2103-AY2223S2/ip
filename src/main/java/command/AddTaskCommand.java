package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

abstract public class AddTaskCommand extends Command {
    public AddTaskCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
