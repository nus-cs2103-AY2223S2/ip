package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * This class is used to mark a task.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for the MarkCommand.
     * @param userInput The user input.
     */
    public MarkCommand(String userInput) {
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
     * Mark the task.
     * @param tasks The database.
     * @param ui The user interface.
     * @param storage The storage.
     * @return String for executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        Task task = tasks.getTaskByIndex(this.taskNumber - 1);
        task.setDone();
        return ui.showMarkTask(task);
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
