package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A representation of the "list" command in Duke. */
public class ListCommand extends Command {

    private final String NAME = "list";

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.displayTasks();
    }
}
