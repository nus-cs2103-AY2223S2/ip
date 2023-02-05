package duke.command;

import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.DukeTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The FindFreeTimeCommand class that finds the nearest date with no tasks
 */
public class FindFreeTimeCommand extends Command {
    private final static String NO_FREE_TIME_MESSAGE = "No free time found in the next month.";
    private final static String NEAREST_FREE_TIME_MESSAGE = "The nearest free time is on %s.";

    /**
     * Execute the find free time command on the tasklist
     *
     * @param tasks tasklist
     * @param ui user interface
     * @param storage storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Get the end date as one month from the current date
        LocalDate endDate = LocalDate.now().plusMonths(1);
        // Assume the nearest free date is the end date
        LocalDate nearestFreeDate = endDate;

        // Iterate through each day in the range of current date to end date
        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            // Check if the current day is free
            if (isDayFree(currentDate, tasks)) {
                // If it is, update the nearest free date
                nearestFreeDate = currentDate;
                break;
            }
            // Move to the next day
            currentDate = currentDate.plusDays(1);
        }

        // If the nearest free date is still the end date, it means there is no free time in the next month
        if (nearestFreeDate.isAfter(endDate) || nearestFreeDate.isEqual(endDate)) {
            ui.appendResponse(NO_FREE_TIME_MESSAGE);
        } else {
            // Otherwise, display the nearest free date
            ui.appendResponse(String.format(NEAREST_FREE_TIME_MESSAGE, nearestFreeDate));
        }
    }

    /**
     * Check whether the given date is free of tasks
     *
     * @param date the date to check
     * @param tasks the tasklist to check against
     * @return true if the date is free of tasks, false otherwise
     */
    private boolean isDayFree(LocalDate date, TaskList tasks) {
        // Iterate through all tasks in the task list
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            DukeTask task = tasks.getTask(i);
            // Check if the task is a DeadlineTask
            if (task instanceof DeadlineTask) {
                if (isDeadlineTaskScheduledOnDate(date, (DeadlineTask) task)) {
                    return false;
                }
            }
            // Check if the task is a EventTask
            else if (task instanceof EventTask) {
                if (isEventTaskScheduledOnDate(date, (EventTask) task)) {
                    return false;
                }
            }
        }
        // If none of the tasks are scheduled on the given date, return true
        return true;
    }

    /**
     * Check whether the given deadline task is scheduled on the given date.
     *
     * @param date the date to check
     * @param task the deadline task to check against
     * @return true if the task is scheduled on the date, false otherwise
     */
    private boolean isDeadlineTaskScheduledOnDate(LocalDate date, DeadlineTask task) {
        return task.getEndDate().equals(date);
    }

    /**
     * Check whether the given event task is scheduled on the given date.
     *
     * @param date the date to check
     * @param task the event task to check against
     * @return true if the task is scheduled on the date, false otherwise
     */
    private boolean isEventTaskScheduledOnDate(LocalDate date, EventTask task) {
        LocalDateTime start = LocalDateTime.of(date, task.getStartDate().toLocalTime());
        LocalDateTime end = LocalDateTime.of(date, task.getEndDate().toLocalTime());
        // Check if the input date is between the start and end date of the event task
        return (start.isAfter(task.getStartDate()) || start.isEqual(task.getStartDate()))
                && (end.isBefore(task.getEndDate()) || end.isEqual(task.getEndDate()));
    }
}