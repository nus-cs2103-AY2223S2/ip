package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

public class ListCommand implements Command {
    public ListCommand() {
    }

    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {
        list.inString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
