package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.getTasks().get(index - 1);
            if (task.getStatusIcon().equals("X")) {
                return "This task is already marked!";
            } else {
                taskList.markTask(index);
            }
            return "Nice! I've marked this task as done: \n" + taskList.formatTaskToString(task);
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }
    public boolean isExit() {
        return false;
    }
}
