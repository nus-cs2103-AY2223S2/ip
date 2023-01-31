package duke.command;

import duke.DukeException;
import duke.Values;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A Command subclass for the mark command.
 */
public class DeleteCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList list, String command) throws DukeException {
        try {
            Task task = list.removeTask(Integer.parseInt(command.split(Values.SPACEX)[1]) - 1);
            ui.pixlPrint("Removed the task:\n" +
                    "\t" + task.formatTask() +
                    "\nYou now have " + list.getSize() + " task(s) in the list.");
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to delete.");
        }
    }
}
