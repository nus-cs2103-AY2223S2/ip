package duke.command;

import duke.exceptions.TaskListInvalidAccessException;
import duke.TaskList;
import duke.Ui;

/**
 * The command to delete a task in the list.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Formats the command with the given storage index.
     * @param idx The provided storage task index.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes this command.
     * @param ts The TaskList instance.
     * @param ui The UI output.
     */
    @Override
    public void execute(TaskList ts, Ui ui) {
        try {
            ts.delete(idx - 1);
        } catch (TaskListInvalidAccessException e) {
            ui.error(e);
        }
    }
}
