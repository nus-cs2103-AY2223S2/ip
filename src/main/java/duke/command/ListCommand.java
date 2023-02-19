package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to output all added tasks in the order they were added in.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.toString();
    }
}
