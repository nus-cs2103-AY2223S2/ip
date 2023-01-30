package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showMessage("OK, I've marked this task as not done yet: ");
        Task cur = taskList.tasks.get(index - 1);
        taskList.unmarkTask(index);
        ui.showMessage("[" + cur.getStatusIcon() + "] " + cur.description);
    }

    public boolean isExit() {
        return false;
    }
}

