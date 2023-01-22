package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents Duke's unmark function.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        Integer taskNum = ui.getTaskNum();
        String s = tasks.unmark(taskNum);
        ui.printWithPartition("\tOK, I've marked this task as not done yet:" + "\n\t  " + s + "\n");
    };

}
