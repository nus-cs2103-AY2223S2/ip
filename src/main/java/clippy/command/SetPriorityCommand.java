package clippy.command;

import clippy.storage.Storage;
import clippy.task.PriorityLevel;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the 'priority' command.
 */
public class SetPriorityCommand extends Command {
    private int taskId;
    private PriorityLevel priority;

    /**
     * Creates a SetPriorityCommand instance.
     *
     * @param taskId The ID of the task to have its priority changed.
     * @param priority The new PriorityLevel to be assigned to the task.
     */
    public SetPriorityCommand(int taskId, PriorityLevel priority) {
        this.taskId = taskId;
        this.priority = priority;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.get(this.taskId).setPriority(this.priority);
        ui.prettyPrint("Got it! I've marked this task with "
                + this.priority.name()
                + " priority: \n"
                + taskList.get(this.taskId).toString());
    }
}
