package duke.model.command;

import duke.model.task.Task;
import duke.model.task.TaskList;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list) {
        Task task = list.get(index);
        task.unmarkAsDone();
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnmarkCommand)) {
            return false;
        }
        UnmarkCommand cmd = (UnmarkCommand) obj;
        return index == cmd.index;
    }
}
