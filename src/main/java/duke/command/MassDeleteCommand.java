package duke.command;

import duke.exception.DukeException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) throws DukeException {
        commandHistory.saveState(tasks);
        deleteDoneTasks(tasks);
        String responseMessage = createResponseMessage(tasks);
        ui.appendResponse(responseMessage);
    }

    /**
     * Deletes all tasks that have been marked as done.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    private void deleteDoneTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            if (tasks.getTask(i).getStatus()) {
                tasks.deleteTask(i);
            }
        }
    }

    /**
     * Creates a response message for the MassDeleteCommand execution.
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @return The response message for the MassDeleteCommand execution
     */
    private String
    createResponseMessage(TaskList tasks) {
        StringBuilder responseMessage = new StringBuilder(DELETE_MESSAGE);
        responseMessage.append(REMAINING_TASKS_MESSAGE);
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            responseMessage.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }
        return responseMessage.toString();
    }
}