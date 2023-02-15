package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.getTasks().get(index-1);
        if (task.getStatusIcon().equals(" ")) {
            return "This task is already unmarked!";
        } else {
            taskList.unmarkTask(index);
            }
        return "OK, I've marked this task as not done yet: \n" + taskList.formatTaskToString(task);
    }

    public boolean isExit() {
        return false;
    }
}

