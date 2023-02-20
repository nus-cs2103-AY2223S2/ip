package voile.model.command;

import java.util.Objects;

import voile.model.task.Task;
import voile.model.task.TaskList;

/**
 * Represents a command that edits a {@code Task}'s description.'
 */
public class EditCommand extends Command {

    private int index;
    private String newDescription;

    /**
     * Creates a new {@code EditCommand} instance.
     *
     * @param index index of the {@code Task} to be edited
     * @param newDescription the new description of that {@code Task}
     */
    public EditCommand(int index, String newDescription) {
        this.index = index;
        this.newDescription = newDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        Task task = list.get(index);
        String oldDescription = task.getDescription();
        task.setDescription(newDescription);
        return String.format(
                "I've edited your task: %s -> %s",
                oldDescription,
                newDescription);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EditCommand)) {
            return false;
        }
        EditCommand cmd = (EditCommand) obj;
        return index == cmd.index && Objects.equals(newDescription, cmd.newDescription);
    }
}
