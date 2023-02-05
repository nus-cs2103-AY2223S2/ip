package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.parser.ErrorMessage;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command{
    private final int taskIndex;
    private final String information;
    private static final String UPDATE_TASK_MESSAGE = "Nice! I've updated the description of this task:\n %s";

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
     *
     * @param ui user interface
     * @param storage storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check if task list is empty
        if (isEmpty(tasks)) {
            String errorMessage = ErrorMessage.TASK_LIST_EMPTY_ERROR + ErrorMessage.ADD_MORE_TASKS;
            throw new InvalidInputException(errorMessage);
        }

        // Check if the task index is valid
        if (!isValidIndex(tasks)) {
            String errorMessage = String.format(ErrorMessage.INVALID_INDEX_ERROR,
                    tasks.getNoOfTasks());
            throw new InvalidInputException(errorMessage);
        }

        // Get the task at the specified index and update its information
        DukeTask currentTask = tasks.getTask(this.taskIndex);
        currentTask.updateInformation(this.information);
        // Construct success message and pass it to UI
        String message = String.format(UPDATE_TASK_MESSAGE, currentTask);
        ui.appendResponse(message);
        // Save the updated task list to storage
        storage.saveTaskList(tasks);
    }
}