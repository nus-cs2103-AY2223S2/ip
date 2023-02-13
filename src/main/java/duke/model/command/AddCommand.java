package duke.model.command;

import java.util.Objects;

import duke.model.task.Task;
import duke.model.task.TaskList;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

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
