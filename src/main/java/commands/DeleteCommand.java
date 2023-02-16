package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * This class is used to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for the DeleteCommand
     * @param userInput The user input.
     */
    public DeleteCommand(String userInput) {
        assert userInput != null;
        this.taskNumber = getTaskNumber(userInput);
    }

    /**
     * Returns the task number from the database.
     * @param userInput The user input.
     * @return The task number from the database.
     */
    private int getTaskNumber(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        return taskNumber;
    }

    /**
     * Delete the task.
     * @param tasks The database.
     * @param ui The user interface.
     * @param storage The storage.s
     * @return String for executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        Task task = tasks.removeTaskByIndex(this.taskNumber - 1);
        return ui.showDeleteTask(task, tasks.getSize());
    }

    /**
     * Check to continue the conversation.
     * @return True.
     */
    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
