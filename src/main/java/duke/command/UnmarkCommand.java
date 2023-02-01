package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

/**
 * UnmarkCommand.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskIndex;

    /**
     * Constructor for UnmarkCommand.
     * @param taskIndex
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes unmarking of task.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(taskIndex);
    }
}
