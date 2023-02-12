package rick.command;

import rick.TaskList;
import rick.Ui;
import rick.exceptions.TaskListInvalidAccessException;

/**
 * Represents the command that marks a task in the list as completed.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class MarkCommand extends Command {
    private final int idx;

    /**
     * Constructs this command with the task index.
     *
     * @param idx The index of the task in storage.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes this command with the given TaskList and UI output, and
     * returns the UI to output to the user.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        try {
            return ts.mark(idx - 1);
        } catch (TaskListInvalidAccessException e) {
            return ui.error(e);
        }
    }
}
