package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit();
}
