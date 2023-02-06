package duke.command;

import duke.TaskList;
import duke.exception.InvalidTaskNumberException;
import duke.task.Task;

/**
 * A command representing the user deleting a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * A constructor for DeleteCommand.
     *
     * @param tasks TaskList object to remove task from
     * @param taskNum Task number of the task to delete
     */
    public DeleteCommand(TaskList tasks, int taskNum) {
        super(tasks);
        this.taskNum = taskNum;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() throws InvalidTaskNumberException {
        Task task = tasks.deleteTask(taskNum);
        return String.format("Noted, I've removed this task:\n   %s\nYou now have %d tasks in the list\n",
                task.toString(), tasks.getNumOfTasks());
    }
}
