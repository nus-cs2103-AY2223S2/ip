package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * ListCommand represents a command to list all tasks.
 */
public class ListCommand extends Command {
    private Ui ui;
    private TaskList taskList;

    /**
     * Creates a ListCommand to list all task of the TaskList.
     *
     * @param ui The ui used.
     * @param taskList The TaskList to list the tasks.
     */
    public ListCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * List all task of the TaskList.
     */
    @Override
    public String action() {
        return ui.listResponse(taskList);
    }
}
