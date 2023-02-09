package duke.command;

import duke.model.TaskList;
import duke.task.Task;

public class RemoveCommand extends Command {

    private int index;

    public RemoveCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list) {
        Task task = list.remove(index);
        return String.format(
                "Noted. I've removed this task:\n" + "  %s\n" + "Now you have %s in the list", task,
                list.countTaskAsString());
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
