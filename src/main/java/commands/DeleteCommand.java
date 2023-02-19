package commands;

import static commands.CommandType.DELETE;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

/**
 * Represents the command that deletes an existing task from the tasklist.
 */
public class DeleteCommand extends Command {
    private static final String UNKNOWN_TASK_MESSAGE = "Oopsies.. Seems like that task does not exist :(";
    private int taskIndex;

    /**
     * Constructs a new DeleteCommand with the specified index of the task.
     *
     * @param taskIndex the index of the task to be deleted in the tasklist
     */
    public DeleteCommand(int taskIndex) {
        super(DELETE);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes this DeleteCommand with a specified TaskList, Ui, and Storage.
     * Deletes the specified Task in the TaskList using the taskIndex, informs the Ui to display the deleted task,
     * and saves the updated TaskList to the file using the Storage object.
     *
     * @param list the TaskList to delete the Task from
     * @param ui the Ui to help inform the user of the deletion
     * @param storage the Storage to save the updated TaskList to
     *
     * @return The execution result string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex >= list.getSize()) {
            return UNKNOWN_TASK_MESSAGE;
        } else {
            assert taskIndex > 0 : "Task Index cannot be less than 1";
            Task toDelete = list.getTask(taskIndex);
            list.deleteTask(taskIndex);
            storage.saveListToFile(list, ui);
            return "Ok. I've removed this task:\n"
                    + toDelete.toString() + "\n Now you have "
                    + list.getSize() + " tasks in the list.";
        }
    }
}
