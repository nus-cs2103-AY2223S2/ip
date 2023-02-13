package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String input) {
        super(input);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(this.getInput(), ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}