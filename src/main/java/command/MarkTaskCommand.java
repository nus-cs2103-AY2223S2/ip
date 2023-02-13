package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

public class MarkTaskCommand extends Command {

    private final boolean isMarked;

    public MarkTaskCommand(String input, boolean isMarked) {
        super(input);
        this.isMarked = isMarked;
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTask(isMarked, this.getInput(), ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}