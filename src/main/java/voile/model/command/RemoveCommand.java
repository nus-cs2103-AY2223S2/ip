package voile.model.command;

import voile.model.task.Task;
import voile.model.task.TaskList;

/**
 * Represents a command that removes a {@code Task} from the {@code TaskList}.
 */
public class RemoveCommand extends Command {

    private int index;

    /**
     * Creates a new {@code RemoveCommand} instance.
     *
     * @param index index of the {@code Task} to be removed
     */
    public RemoveCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        Task task = list.remove(index);
        int size = list.size();
        return String.format(
                "I've removed this task:\n%s\nThere %s %d task%s in the library.",
                task, size < 2 ? "is" : "are",
                size, size < 2 ? "" : "s");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RemoveCommand)) {
            return false;
        }
        RemoveCommand cmd = (RemoveCommand) obj;
        return index == cmd.index;
    }
}
