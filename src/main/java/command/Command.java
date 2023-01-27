package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

public interface Command {
    public void run(TaskList tasks, Ui ui, Storage storage);
}
