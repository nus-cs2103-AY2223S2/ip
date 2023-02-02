package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * The type List command.
 */
public class ListCommand extends Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.showList();
    }
}
