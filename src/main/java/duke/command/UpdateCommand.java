package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command{
    private final int taskIndex;
    private final String information;

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
     * Executes the update task command, updates the task's information and save the tasklist to storage
     *
     * @param tasks the tasklist to update the task from
     * @param ui the user interface to display response messages
     * @param storage the storage for the tasklist
     * @throws DukeException if the task list is empty or if the input index is not within the range of [1,
     * tasks.getNoOfTasks()]
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isEmpty(tasks)) {
            String errorMessage = "OOPS!!! Your task list is currently empty";
            throw new InvalidInputException(errorMessage + "\nPlease add in more tasks");
        }
        if (!isValidIndex(tasks)) {
            String errorMessage = "OOPS!!! The input index is not within the range of [1, "
                    + tasks.getNoOfTasks() + "]";
            throw new InvalidInputException(errorMessage + "\nPlease input a valid index");
        }
        DukeTask currentTask = tasks.getTask(this.taskIndex);
        currentTask.updateInformation(this.information);
        String message = "Nice! I've updated the description of this task:\n " + currentTask;
        ui.appendResponse(message);
        storage.saveTaskList(tasks);
    }
}
