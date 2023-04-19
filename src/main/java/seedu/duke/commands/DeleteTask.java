package seedu.duke.commands;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.tasks.Task;
import seedu.duke.ui.Ui;

public class DeleteTask implements Command {
    private int taskIndex;

    public DeleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command with the specified TaskList, Ui, and, Storage
     * @param tasks The TaskList object containing the tasks
     * @param ui The Ui object handling input/output
     * @param storage The Storage object handling persistent storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removed = tasks.remove(taskIndex - 1);
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
        ui.showDelete();
    }

    /**
     * Returns whether the command is an Exit command
     * @return False
     */
    public boolean isExit() {
        return false;
    }
}
