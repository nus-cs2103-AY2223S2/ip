package duke.command;

import java.util.Objects;

import duke.task.Task;
import duke.util.container.TaskList;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList list) {
        list.add(task);
        return String.format(
                "Got it. I've added this task:\n" + "  %s\n" + "Now you have %s in the list.", task,
                list.countTaskAsString());
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
