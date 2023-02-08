package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The type List command.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // gets concatenated String of tasks in the format {task number}. {task string}
        return tasks.showList();
    }
}
