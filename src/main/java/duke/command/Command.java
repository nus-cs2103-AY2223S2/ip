package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the abstract Command class which serves as a base template to handle all possible commands by Duke.
 */
public abstract class Command {

    /**
     * Abstract init method for command class.
     * @param tasks a list of tasks.
     * @param ui Ui class to handle display messages.
     * @param storage Storage to handle saving/loading of data to/from the list of task.
     * @return duke's response message.
     */
    public abstract String initCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
