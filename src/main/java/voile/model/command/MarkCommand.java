package voile.model.command;

import voile.model.task.Task;
import voile.model.task.TaskList;

/**
 * Represents a command that marks a specific task as done.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Creates a new {@code MarkCommand} instance.
     *
     * @param index index of the {@code Task} to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        Task task = list.get(index);
        task.markAsDone();
        return "I've marked this task as done:\n" + task + "Good job!";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MarkCommand)) {
            return false;
        }
        MarkCommand cmd = (MarkCommand) obj;
        return index == cmd.index;
    }
}
