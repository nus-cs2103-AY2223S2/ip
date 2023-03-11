package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.exceptions.TaskNotFoundException;
import roody.tasks.Task;

/**
 * Represents an Unmark command.
 */
public class UnmarkCommand extends Command {
    private int taskindex;

    /**
     * Creates an Unmark command.
     * @param taskindex Index of task to unmark.
     */
    public UnmarkCommand(int taskindex) {
        this.taskindex = taskindex;
    }
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws TaskNotFoundException {
        if (taskindex < 0 || taskindex > taskList.size() - 1 || taskList.get(taskindex) == null) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.get(taskindex);
        task.setUnDone();
        return ui.showUnmarkStatus(task);
    }
}
