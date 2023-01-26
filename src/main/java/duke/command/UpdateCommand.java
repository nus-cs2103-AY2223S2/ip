package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.StorageFileException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command{
    private final int taskIndex;
    private final String information;
    private static final String EMPTY_TASK_LIST_ERROR = "OOPS!!! Your task list is currently empty";
    private static final String INVALID_INDEX_ERROR = "OOPS!!! The input index is not within the range of [1, %d]";
    private static final String TASK_UPDATED_MESSAGE = "Nice! I've updated the description of this task:\n ";

    /**
     * Constructor of UpdateCommand that takes in the index of the task to marked.
     *
     * @param taskIndex The index of the task to be marked
     * @param information The new information of the task
     */
    public UpdateCommand(int taskIndex, String information) {
        this.taskIndex = taskIndex;
        this.information = information;
    }

    /**
     * Check whether the given list is empty.
     *
     * @param list The given list to be checked
     * @return Whether the given list is empty
     */
    public boolean isEmpty(TaskList list) {
        return list.getNoOfTasks() == 0;
    }

    /**
     * Checks whether the index is valid with respect to the given list.
     *
     * @param list The given list to be checked
     * @return Whether the given index is valid
     */
    public boolean isValidIndex(TaskList list) {
        return taskIndex >= 0 && taskIndex < list.getNoOfTasks();
    }

    /**
     * Execute the update command on the tasklist
     * @param ui user interface
     * @param storage storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) {
        commandHistory.saveState(tasks);
        try {
            if (isEmpty(tasks)) {
                throw new InvalidInputException(EMPTY_TASK_LIST_ERROR + "\nPlease add in more tasks");
            }
            if (!isValidIndex(tasks)) {
                throw new InvalidInputException(String.format(INVALID_INDEX_ERROR + "\nPlease input a valid index",
                        tasks.getNoOfTasks()));
            }
            updateTaskInformation(tasks, ui);
            saveTaskList(tasks, storage);
        } catch (InvalidInputException | StorageFileException e) {
            ui.appendResponse(e.getMessage());
        }
    }

    /**
     * Update the task information
     * @param tasks tasklist
     * @param ui user interface
     */
    private void updateTaskInformation(TaskList tasks, Ui ui) {
        DukeTask currentTask = tasks.getTask(this.taskIndex);
        currentTask.updateInformation(this.information);
        String message = TASK_UPDATED_MESSAGE + currentTask;
        ui.appendResponse(message);
    }

    /**
    * Save the task list to storage
    * @param tasks tasklist
    * @param storage storage
    */
    private void saveTaskList(TaskList tasks, Storage storage) throws StorageFileException {
        storage.saveTaskList(tasks);
    }
}