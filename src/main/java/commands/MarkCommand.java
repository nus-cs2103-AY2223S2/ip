package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;
import tasks.Task;

public class MarkCommand implements Command{
    private String input;
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Marks the task as done.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTaskList().get(getIndex() - 1);
        task.markAsDone();
        storage.store(taskList);
        return ui.markMessage(task);
    }

    /**
     * Returns the index of the task to be marked as done.
     *
     * @return The index of the task to be marked as done.
     */
    public Integer getIndex() {
        return Integer.valueOf(input.split(" ")[1]);
    }
}
