package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.listTask(this.getInput());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}