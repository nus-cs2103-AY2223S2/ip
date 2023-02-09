package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.exceptions.TaskNotFoundException;
import roody.tasks.Task;



/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    private int taskindex;

    /**
     * Creates a mark command.
     * @param taskindex Index of task to be marked.
     */
    public MarkCommand(int taskindex) {
        this.taskindex = taskindex;
    }
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws TaskNotFoundException {
        if (taskindex < 0 || taskindex > taskList.size() - 1 || taskList.get(taskindex) == null) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.get(taskindex);
        task.setDone();
        return ui.showMarkStatus(task);
    }
}
