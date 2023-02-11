package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

public class UnMarkCommand implements Command {
    private int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {
        list.unmarkTask(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
