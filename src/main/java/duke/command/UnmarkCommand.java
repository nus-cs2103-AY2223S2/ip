package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task cur = taskList.getTasks().get(index-1);
        if (cur.getStatusIcon().equals(" ")) {
            return "This task is already unmarked!";
        } else {
            taskList.unmarkTask(index);
            String taskString = "[" + cur.getStatusIcon() + "] " + cur.getDescription();
            if (!(cur instanceof Todo)) {
                taskString += " (" + cur.getDuedateString() + ")";
            }
            return "OK, I've marked this task as not done yet: \n" + taskString;
        }
    }

    public boolean isExit() {
        return false;
    }
}

