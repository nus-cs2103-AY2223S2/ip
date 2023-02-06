package rick.command;

import rick.TaskList;
import rick.Ui;
import rick.exceptions.TaskListInvalidAccessException;

/**
 * The command that marks a task in the list as not completed.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class UnmarkCommand extends Command {
    private final int idx;

    /**
     * Formats this with the index of the storage to access and modify the
     * task.
     *
     * @param idx The storage index.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes this task.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        try {
            return ts.unMark(idx - 1);
        } catch (TaskListInvalidAccessException e) {
            return ui.error(e);
        }
    }
}
