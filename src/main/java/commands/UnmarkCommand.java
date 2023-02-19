package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

import tasks.Task;

public class UnmarkCommand implements Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Marks a task as not done.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTaskList().get(getIndex(input) - 1);
        task.markAsNotDone();
        storage.store(taskList);
        return ui.unmarkMessage(task);
    }

    /**
     * Returns the index of the task to be marked as not done.
     *
     * @return The index of the task to be marked as not done.
     */
    public Integer getIndex(String string) {
        return Integer.valueOf(string.split(" ")[1]);
    }
}
