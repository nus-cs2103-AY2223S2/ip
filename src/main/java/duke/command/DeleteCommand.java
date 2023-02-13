package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task cur = taskList.deleteTask(index);
        assert !taskList.getTasks().contains(cur) : "Task should be deleted from tasklist";
        String taskString = "[" + cur.getStatusIcon() + "] " + cur.getDescription();
        if (!(cur instanceof Todo)) {
            taskString +=  " (" + cur.getDuedateString() + ")";
        }
        return "Noted, I've removed this task: \n" + taskString + "\n Now you have " + taskList.getNumTasks() + " tasks in the list.";
    }

    public boolean isExit() {
        return false;
    }
}

