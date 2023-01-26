package duke.command;

import duke.exception.InvalidInputException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.DukeTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
/**
 * The FindFreeTimeCommand class that finds the nearest date with no tasks
 */
public class FindFreeTimeCommand extends Command {
    private final static String noFreeTimeMessage = "No free time found in the next month.";
    private final static String nearestFreeTimeMessage = "The nearest free time is on %s.";

    /**
     * Execute the find free time command on the tasklist
     *
     * @param tasks tasklist
     * @param ui user interface
     * @param storage storage
     * @param commandHistory command history
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory)
            throws InvalidInputException {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusMonths(1);
        LocalDate nearestFreeDate = endDate;

        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            if (isDayFree(currentDate, tasks)) {
                nearestFreeDate = currentDate;
                break;
            }
            currentDate = currentDate.plusDays(1);
        }

        if (nearestFreeDate.isAfter(endDate) || nearestFreeDate.isEqual(endDate)) {
            ui.appendResponse(noFreeTimeMessage);
        } else {
            ui.appendResponse(String.format(nearestFreeTimeMessage, nearestFreeDate));
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
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            DukeTask task = tasks.getTask(i);
            if (task instanceof DeadlineTask) {
                if (isDeadlineTaskScheduledOnDate(date, (DeadlineTask) task)) {
                    return false;
                }
            } else if (task instanceof EventTask) {
                if (isEventTaskScheduledOnDate(date, (EventTask) task)) {
                    return false;
                }
            }
        }
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
     *
     * Check whether the given event task is scheduled on the given date.
     * @param date the date to check
     * @param task the event task to check against
     * @return true if the task is scheduled on the date, false otherwise
     */
    private boolean isEventTaskScheduledOnDate(LocalDate date, EventTask task) {
        return (date.isAfter(task.getStartDate()) || date.isEqual(task.getStartDate()))
                && (date.isBefore(task.getEndDate()) || date.isEqual(task.getEndDate()));
    }
}