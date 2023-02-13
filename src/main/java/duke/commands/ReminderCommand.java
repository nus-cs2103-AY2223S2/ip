package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.uitext.UiText;

/**
 * Represents a command to display upcoming tasks. These are events that start soon and deadlines that end soon.
 */
public class ReminderCommand extends Command {
    // The maximum number of days between today and the event start or deadline for reminder command to display it.
    private static final long DAYS_TO_TASK_FOR_REMINDER = 2;

    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) {
        assert DAYS_TO_TASK_FOR_REMINDER >= 0 : "Number of days cannot be negative";
        TaskList upcomingTasks = taskList.getUpcomingTasks(DAYS_TO_TASK_FOR_REMINDER);
        if (upcomingTasks.getSize() == 0) {
            return "Wonderful. You have no tasks for " + DAYS_TO_TASK_FOR_REMINDER + " days.";
        }
        return "REMINDER! These tasks are coming up:\n"
                + upcomingTasks;
    }
}
