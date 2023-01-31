package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(inputArr, ui);
        storage.writeData(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
