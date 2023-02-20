package voile.model.command;

import java.util.Objects;

import voile.model.task.Task;
import voile.model.task.TaskList;

/**
 * Represents a command that adds a new {@code Task}.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates a {@code AddCommand} instance.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        list.add(task);
        return String.format(
                "I've added this task:\n%s\nNow you have %s in the list.",
                task,
                list.countTasks());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AddCommand)) {
            return false;
        }
        AddCommand cmd = (AddCommand) obj;
        return Objects.equals(task, cmd.task);
    }
}
