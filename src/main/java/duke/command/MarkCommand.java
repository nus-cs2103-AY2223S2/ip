package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showMessage("Nice! I've marked this task as done: ");
        Task cur = taskList.getTasks().get(index - 1);
        taskList.markTask(index);
        ui.showMessage("[" + cur.getStatusIcon() + "] " + cur.getDescription());
    }
    public boolean isExit() {
        return false;
    }
}
