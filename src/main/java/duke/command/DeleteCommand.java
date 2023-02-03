package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showMessage("Noted, I've removed this task: ");
        Task cur = taskList.getTasks().get(index - 1);
        taskList.deleteTask(index);
        ui.showMessage("[" + cur.getStatusIcon() + "] " + cur.getDescription());
        ui.showMessage("Now you have " + taskList.getNumTasks() + " tasks in the list.");
    }

    public boolean isExit() {
        return false;
    }
}

