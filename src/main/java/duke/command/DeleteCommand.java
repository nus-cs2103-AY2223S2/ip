package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

/**
 * DeleteCommand that has task index.
 */
public class DeleteCommand extends AddCommand {
    public static final String COMMAND_WORD = "delete";
    private int taskIndex;

    /**
     * Constructor for DeleteCommand
     * @param taskIndex
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes deletion of task from tasklist.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskIndex);
    }
}