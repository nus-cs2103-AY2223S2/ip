package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command containing an action on one or more Miki components.
 */
public interface Command {
    /**
     * Performs the action of this command.
     *
     * @param tasks tasklist to perform the action on
     * @param ui ui to perform the action on
     * @param storage storage to perform the action on
     */
    public void run(TaskList tasks, Ui ui, Storage storage);
}
