package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Values;
import duke.gui.Ui;
import duke.task.Task;

/**
 * A Command subclass for the mark command.
 */
public class UnmarkCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList list, String command) throws DukeException {
        try {
            Task task = list.getTask(Integer.parseInt(command.split(Values.SPACEX)[1]) - 1);
            task.uncomplete();

            return ui.pixlPrint("Un-doing the task...\n"
                    + "\t" + task.formatTask());
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to unmark.");
        }
    }
}
