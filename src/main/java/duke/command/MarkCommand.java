package duke.command;

import duke.TaskList;
import duke.exception.InvalidTaskNumberException;
import duke.task.Task;

/**
 * A command representing the user marking a task as completed.
 */
public class MarkCommand extends Command {
    private int taskNum;

    /**
     * A constructor for MarkCommand.
     *
     * @param tasks TaskList object containing task to mark as completed
     * @param taskNum Task number of task to mark as completed
     */
    public MarkCommand(TaskList tasks, int taskNum) {
        super(tasks);
        this.taskNum = taskNum;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() throws InvalidTaskNumberException {
        Task task = tasks.markTask(taskNum);
        return String.format("Nice! I've marked this task as done:\n   %s\n", task);
    }
}
