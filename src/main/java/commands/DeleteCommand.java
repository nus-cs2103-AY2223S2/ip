package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class DeleteCommand implements Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTaskList().remove(getIndex() - 1);
        storage.store(taskList.getTaskList());
    }

    /**
     * Returns the index of the task to be deleted.
     *
     * @return The index of the task to be deleted.
     */
    public Integer getIndex() {
        return Integer.valueOf(input.split(" ")[1]);
    }
}
