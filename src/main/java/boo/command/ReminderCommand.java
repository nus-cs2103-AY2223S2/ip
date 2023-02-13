package boo.command;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

import boo.datetime.DateTime;
import boo.task.Deadline;
import boo.task.Event;
import boo.task.Task;
import boo.task.ToDo;
import boo.tasklist.TaskList;


/**
 * Represents a "reminder" command that is entered by the user.
 */
public class ReminderCommand extends Command {

    /** The period within which the tasks must fall to be reminded */
    private String reminderDuration;

    /** The task list to check through. */
    private TaskList tasks;

    /**
     * Constructs a {@code ReminderCommand}.
     *
     * @param reminderDuration The duration to check for tasks that are either going to be due or upcoming.
     * @param tasks The task list that contains all the tasks to look through.
     */
    public ReminderCommand(String reminderDuration, TaskList tasks) {
        super();
        this.reminderDuration = reminderDuration;
        this.tasks = tasks;

    }

    /**
     * Returns the list of tasks that are either due or upcoming within the given reminder time duration.
     * For example, if the reminder time duration is 1 day, and we let reminderDate be 1 day from the current time
     * this method is called, then all deadlines due on or before reminderDate and all events that either end or span
     * over reminderDate will be printed out.
     *
     * @return the list of supported commands
     */
    @Override
    public String runCommand() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime cutOff = getCutOff(currentDateTime);

        StringBuilder sb = new StringBuilder();
        sb.append(prepareReminderMessageIntro(currentDateTime, cutOff));

        int count = 1;

        //Iterate through task by task
        for (int i = 0; i < tasks.getSizeOfTaskList(); i = i + 1) {
            Task currentTask = tasks.getTask(i);
            if (currentTask instanceof ToDo) {
                //Do nothing as a to-do task is not time-sensitive
            } else if (currentTask instanceof Deadline) {
                Temporal taskDeadline = ((Deadline) currentTask).getDeadline();
                if (DateTime.fallWithinPeriod(currentDateTime, cutOff, taskDeadline)) {
                    sb.append(count).append(". ").append(currentTask.getStatusOfTaskInString()).append("\n");
                    count += 1;
                }
            } else if (currentTask instanceof Event) {
                Temporal eventStartDate = ((Event) currentTask).getStartDate();
                Temporal eventEndDate = ((Event) currentTask).getEndDate();
                if (DateTime.isStrictlyBefore(eventEndDate, currentDateTime)) {
                    continue;
                }
                if (DateTime.isStrictlyBefore(cutOff, eventStartDate)) {
                    continue;
                }
                if (DateTime.isValidDuration(eventStartDate, currentDateTime)) {
                    sb.append(count).append(". ").append(currentTask.getStatusOfTaskInString())
                            .append(" [ ONGOING ]").append("\n");
                    count += 1;
                }
                if (DateTime.isStrictlyBefore(currentDateTime, eventStartDate)) {
                    sb.append(count).append(". ").append(currentTask.getStatusOfTaskInString())
                            .append(" [ UPCOMING ]").append("\n");
                    count += 1;
                }
            }
        }

        if (count == 1) {
            sb.append("\nNo upcoming tasks. Yay! Let's relax!");
        }

        return sb.toString();
    }


    /**
     * Gets the cut-off date and time for which tasks happening after it will not be included in
     * the reminder command.
     *
     * @param currentDateTime The date and time at which the reminder command is called.
     * @return a LocalDateTime object representing the cut-off date and time
     */
    public LocalDateTime getCutOff(LocalDateTime currentDateTime) {
        assert reminderDuration != null : "Invalid reminder duration. Cannot be null.";
        LocalDateTime cutOff = null;

        switch (reminderDuration) {
        case "day":
            cutOff = currentDateTime.plusDays(1);
            break;
        case "week":
            cutOff = currentDateTime.plusWeeks(1);
            break;
        case "month":
            cutOff = currentDateTime.plusMonths(1);
            break;
        default:
            //Command can only be day, month or year. Should not reach here.
            assert false;
        }
        return cutOff;
    }


    /**
     * Prepares the introductory string message that is printed at the start of the reminder command.
     *
     * @param currentDateTime The date and time at which the reminder command is called.
     * @param cutOff The date and time for which tasks happening after it will not be included in the reminder
     *               command.
     * @return
     */
    public String prepareReminderMessageIntro(LocalDateTime currentDateTime, LocalDateTime cutOff) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks from ");
        sb.append(DateTime.formatDate(currentDateTime));
        sb.append(" to ");
        sb.append(DateTime.formatDate(cutOff));
        sb.append(": \n\n");
        return sb.toString();
    }
}
