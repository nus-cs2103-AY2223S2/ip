package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A MassDeleteCommand class that encapsulates the action of delete all the done events.
 */
public class MassDeleteCommand extends Command {
    private static final String DELETE_MESSAGE = "I have deleted all the tasks that have been marked as done.\n";
    private static final String REMAINING_TASKS_MESSAGE = "Here are the remaining tasks in your list:\n";

    /**
     * Executes the command to delete all tasks that have been marked as done.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     * @throws DukeException Throws exception if there is an error in the execution of the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Delete done tasks from the task list
        deleteDoneTasks(tasks);
        // Create a response message containing the updated task list
        String responseMessage = createResponseMessage(tasks);
        // Append the response message to the UI
        ui.appendResponse(responseMessage);
    }

    /**
     * Deletes all tasks that have been marked as done.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    private void deleteDoneTasks(TaskList tasks) {
        // loop through all tasks
        for (int i = tasks.getNoOfTasks() - 1; i >= 0; i--) {
            // check if task at index i is done
            if (tasks.getTask(i).getStatus()) {
                // if task is done, delete the task from the task list
                tasks.deleteTask(i);
            }
        }
    }

    /**
     * Creates a response message for the MassDeleteCommand execution.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @return The response message for the MassDeleteCommand execution
     */
    private String createResponseMessage(TaskList tasks) {
        StringBuilder responseMessage = new StringBuilder(DELETE_MESSAGE);
        //append the message to notify the user of the remaining tasks
        responseMessage.append(REMAINING_TASKS_MESSAGE);

        //loop through the list of remaining tasks
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            //append the task number, task and a newline character to the response message
            responseMessage.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }

        //return the response message
        return responseMessage.toString();
    }
}
