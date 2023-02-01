package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

/**
 * ListCommand.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes listing of tasks in task list.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
