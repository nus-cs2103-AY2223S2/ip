package duke.command;

import duke.TaskList;
import duke.exception.InvalidTaskNumberException;
import duke.task.Task;

/**
 * A command representing the user marking a task as not completed yet
 */
public class UnmarkCommand extends Command {
    private int taskNum;

    /**
     * A constructor for UnmarkCommand.
     *
     * @param tasks Tasklist object that contains task to be marked as not completed yet
     * @param taskNum Task number of task to be marked as not completed yet
     */
    public UnmarkCommand(TaskList tasks, int taskNum) {
        super(tasks);
        this.taskNum = taskNum;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() throws InvalidTaskNumberException {
        Task task = tasks.unmarkTask(taskNum);
        return String.format("OK, I've unmarked this task as not done yet:\n   %s\n", task);
    }
}
