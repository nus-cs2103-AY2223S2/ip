package voile.model.command;

import voile.model.task.Task;
import voile.model.task.TaskList;

/**
 * Represents a command that marks a {@code Task} as not done yet.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Create a new {@code UnmarkCommand} instance.
     *
     * @param index index of the {@code Task} to be marked as not done yet
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        Task task = list.get(index);
        task.unmarkAsDone();
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnmarkCommand)) {
            return false;
        }
        UnmarkCommand cmd = (UnmarkCommand) obj;
        return index == cmd.index;
    }
}
