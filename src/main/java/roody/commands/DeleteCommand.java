package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.exceptions.RoodyException;
import roody.exceptions.TaskNotFoundException;
import roody.tasks.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskindex;

    /**
     * Creates a delete command.
     * @param taskIndex Index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskindex = taskIndex;
    }
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws RoodyException {
        if (taskindex < 0 || taskindex > taskList.size() - 1 || taskList.get(taskindex) == null) {
            throw new TaskNotFoundException();
        } else {
            Task task = taskList.get(taskindex);
            taskList.remove(taskindex);
            return ui.showDeleteTask(task, taskList.size());
        }
    }
}
