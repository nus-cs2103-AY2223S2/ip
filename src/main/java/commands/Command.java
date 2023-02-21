package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

/**
 * Represents a command.
 */
public interface Command {
    String execute(TaskList taskList, Ui ui, Storage storage);
}

