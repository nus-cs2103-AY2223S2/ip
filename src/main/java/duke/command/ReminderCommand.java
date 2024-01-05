package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A ReminderCommand class the encapsulates the action of displaying
 * all the upcoming deadline task and event tasks.
 */
public class ReminderCommand extends Command {
    private static final String REMINDER_MESSAGE = "Here are the upcoming deadlines:\n";

    /**
     * Displays all the upcoming deadlines.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //Create a new TaskList to store all the tasks with deadlines
        TaskList deadlineList = tasks.extractDeadlines();
        //Create a response message for the tasks with deadlines
        String responseMessage = createResponseMessage(deadlineList);
        //append the response message to the UI
        ui.appendResponse(responseMessage);
    }

    /**
     * Create the response message for the ReminderCommand.
     *
     * @param deadlineList The TaskList that contains all the upcoming deadlines
     * @return The response message
     */
    private String createResponseMessage(TaskList deadlineList) {
        //Creating a new string builder to store the response message
        StringBuilder responseMessage = new StringBuilder(REMINDER_MESSAGE);
        //Iterating through the tasks in the deadlineList

        for (int i = 0; i < deadlineList.getNoOfTasks(); i++) {
            //Appending the task number, task details and newline character to the response message
            responseMessage.append(i + 1).append(".").append(deadlineList.getTask(i)).append("\n");
        }

        //Returning the final response message as a string
        return responseMessage.toString();
    }
}
