package duke.model.command;

import duke.model.task.Task;
import duke.model.task.TaskList;

public class RemoveCommand extends Command {

    private int index;

    public RemoveCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list) {
        Task task = list.remove(index);
        return String.format(
                "I've removed this task:\n%s\nNow you have %s in the list",
                task,
                list.countTasks());
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
