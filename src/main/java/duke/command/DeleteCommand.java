package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.getTasks().get(index - 1);
        String taskString = taskList.formatTaskToString(task);
        taskList.deleteTask(index);
        assert !taskList.getTasks().contains(task) : "Task should be deleted from tasklist";
        return "Noted, I've removed this task: \n" + taskString + "\nNow you have " + taskList.getNumTasks() + " tasks in the list.";
    }

    public boolean isExit() {
        return false;
    }
}

