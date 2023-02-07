package duke.command;

import java.util.Objects;

import duke.TaskList;
import duke.task.Task;

public class AddTaskCommand extends Command {

    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    String tryExecute(TaskList list) {
        list.add(task);
        return String.format(
                "Got it. I've added this task:\n" + "  %s\n" + "Now you have %s in the list.", task,
                list.countTaskAsString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AddTaskCommand)) {
            return false;
        }
        AddTaskCommand cmd = (AddTaskCommand) obj;
        return Objects.equals(task, cmd.task);
    }

}
