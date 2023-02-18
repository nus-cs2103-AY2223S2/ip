package duke.model.command;

import java.util.Objects;

import duke.model.task.Task;
import duke.model.task.TaskList;

public class EditCommand extends Command {

    private int index;
    private String newDescription;

    public EditCommand(int index, String newDescription) {
        this.index = index;
        this.newDescription = newDescription;
    }

    @Override
    public String execute(TaskList list) {
        Task task = list.get(index);
        String oldDescription = task.getDescription();
        task.setDescription(newDescription);
        return String.format(
                "I've edited your task's description: %s -> %s",
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
