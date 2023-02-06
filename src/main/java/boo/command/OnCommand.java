package boo.command;

import java.time.temporal.Temporal;

import boo.datetime.DateTime;
import boo.task.Deadline;
import boo.task.Event;
import boo.task.Task;
import boo.tasklist.TaskList;

/**
 * Represents an on command that is entered by the user to check what tasks are there on a specific day.
 */
public class OnCommand extends Command {
    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Date object representing the date to be checked. */
    private Temporal dateObject;

    /**
     * Constructs an OnCommand
     *
     * @param tasks The {@code TaskList} of all available tasks.
     * @param date The date to be checked.
     */
    public OnCommand(String date, TaskList tasks) {
        super();
        this.dateObject = DateTime.getDateTimeObject(date);
        this.tasks = tasks;
    }

    /**
     * Gets all the tasks that occur on the given date.
     *
     * @return a string containing all the tasks that occur on the given date.
     */
    @Override
    public String runCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks on: ");
        sb.append(DateTime.formatDate(dateObject));
        sb.append("\n\n");

        int count = 1;
        //Iterate through each task and check
        for (int t = 0; t < tasks.getSizeOfTaskList(); t = t + 1) {
            Task currTask = tasks.getTask(t);
            if (currTask instanceof Deadline) {
                if (DateTime.isEqualDate(dateObject, ((Deadline) currTask).getDeadline())) {
                    sb.append(count);
                    sb.append(". ");
                    sb.append(currTask.getStatusOfTaskInString());
                    sb.append("\n");
                    count += 1;
                }
            } else if (currTask instanceof Event) {
                if (DateTime.isValidDuration(((Event) currTask).getStartDate(), dateObject)
                        && DateTime.isValidDuration(dateObject, ((Event) currTask).getEndDate())) {
                    sb.append(count);
                    sb.append(". ");
                    sb.append(currTask.getStatusOfTaskInString());
                    sb.append("\n");
                    count += 1;
                }
            }
        }
        if (count == 1) {
            sb.append("You have no tasks on this day.");
        }
        return sb.toString();
    }
}
