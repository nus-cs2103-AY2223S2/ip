package duke.command;

import duke.TaskList;
import duke.task.Task;

public class RemoveTaskCommand extends Command {

    private int index;

    public RemoveTaskCommand(int index) {
        this.index = index;
    }

    @Override
    String tryExecute(TaskList list) {
        Task task = list.remove(index);
        return String.format(
                "Noted. I've removed this task:\n" + "  %s\n" + "Now you have %s in the list", task,
                list.countTaskAsString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RemoveTaskCommand)) {
            return false;
        }
        RemoveTaskCommand cmd = (RemoveTaskCommand) obj;
        return index == cmd.index;
    }

}
