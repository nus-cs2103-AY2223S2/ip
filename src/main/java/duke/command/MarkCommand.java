package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task cur = taskList.getTasks().get(index-1);
        if (cur.getStatusIcon().equals("X")) {
            return "This task is already marked!";
        } else {
            taskList.markTask(index);
            String taskString = "[" + cur.getStatusIcon() + "] " + cur.getDescription();
            if (!(cur instanceof Todo)) {
                taskString += " (" + cur.getDuedateString() + ")";
            }
            return "Nice! I've marked this task as done: \n" + taskString;
        }
    }
    public boolean isExit() {
        return false;
    }
}
