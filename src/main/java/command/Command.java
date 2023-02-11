package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

public interface Command {
    public void execute(Ui ui, Tasklist list, Storage storage);
    public boolean isExit();

}
