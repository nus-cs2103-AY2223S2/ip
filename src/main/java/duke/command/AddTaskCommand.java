package duke.command;

import java.util.Objects;

import duke.TaskList;
import duke.UI;
import duke.task.Task;

public class AddTaskCommand extends Command {

    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList list) {
        list.add(task);
        String displayedMsg = String.format(
                "Got it. I've added this task:\n" + "  %s\n" + "Now you have %s in the list.", task,
                list.countTaskAsString());
        UI.echo(displayedMsg);
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
