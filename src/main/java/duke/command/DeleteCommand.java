package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.gui.Ui;
import duke.Values;
import duke.task.Task;

/**
 * A Command subclass for the mark command.
 */
public class DeleteCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList list, String command) throws DukeException {
        try {
            int taskNumIndex = 1; // Index of the task number in the command.
            Task task = list.removeTask(
                    Integer.parseInt(command.split(Values.SPACEX)[taskNumIndex]) - 1);

            return ui.pixlPrint("Removed the task:\n"
                    + "\t" + task.formatTask()
                    + "\nYou now have " + list.getSize() + " task(s) in the list.");
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to delete.");
        }
    }
}
