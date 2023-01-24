package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public interface Command {
    String getCommandName();

    String getCommandRegexPattern();

    String getCommandPattern();

    void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException;
}
