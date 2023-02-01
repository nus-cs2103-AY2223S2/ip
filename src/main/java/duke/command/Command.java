package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Interface for all commands and the required methods.
 */
public interface Command {
    String getCommandName();

    String getCommandRegexPattern();

    String getCommandPattern();

    void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException;
}
