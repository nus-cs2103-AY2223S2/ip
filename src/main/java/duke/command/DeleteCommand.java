package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {

    private final TaskList taskList;
    private final int taskIndex;
    private final Ui ui;

    /**
     * Constructor for DeleteCommand.
     * Deletes a task from the task list.
     * @param taskList TaskList of Duke's tasks.
     * @param taskIndex Index of the task in the task list.
     * @param ui Ui instance of Duke.
     */
    public DeleteCommand(TaskList taskList, int taskIndex, Ui ui) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
        this.ui = ui;
    }

    @Override
    public boolean execute() throws DukeException {
        taskList.remove(taskIndex);

        ui.showTaskDeletedMessage();
        return false;
    }
}
