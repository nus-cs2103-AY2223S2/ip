package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

/**
 * MarkCommand that has task index.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int taskIndex;

    /**
     * Constructor for MarkCommand.
     * @param taskIndex
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes marking of task.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(taskIndex);
    }
}
