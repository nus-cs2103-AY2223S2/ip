package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

public class DeleteCommand implements Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {
        list.deleteTask(this.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
