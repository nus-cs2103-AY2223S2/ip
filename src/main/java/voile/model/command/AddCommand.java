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
        int size = list.size();
        return String.format(
                "I've added this task:\n%s\nThere %s %d task%s in the library.",
                task, size < 2 ? "is" : "are",
                size, size < 2 ? "" : "s");
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
