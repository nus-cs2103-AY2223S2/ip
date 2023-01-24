package duke.command;

import duke.exception.DukeException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ReminderCommand extends Command{
    private static final String REMINDER_MESSAGE = "Here are the upcoming deadlines:\n";

    /**
     * Displays all the upcoming deadlines.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) {
        TaskList deadlineList = tasks.extractDeadlines();
        String responseMessage = createResponseMessage(deadlineList);
        ui.appendResponse(responseMessage);
    }

    /**
     * Create the response message for the ReminderCommand.
     *
     * @param deadlineList The TaskList that contains all the upcoming deadlines
     * @return The response message
     */
    private String createResponseMessage(TaskList deadlineList) {
        StringBuilder responseMessage = new StringBuilder(REMINDER_MESSAGE);
        for (int i = 0; i < deadlineList.getNoOfTasks(); i++) {
            responseMessage.append(i + 1).append(".").append(deadlineList.getTask(i)).append("\n");
        }
        return responseMessage.toString();
    }
}
