package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ListCommand extends Command {

    private final String NAME = "list";

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.displayTasks();
    }
}
