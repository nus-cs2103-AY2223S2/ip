package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

public class FindTaskCommand extends Command{

    public FindTaskCommand(String input) {
        super(input);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.findTask(this.getInput(), ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
