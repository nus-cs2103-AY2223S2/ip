package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage);
}

