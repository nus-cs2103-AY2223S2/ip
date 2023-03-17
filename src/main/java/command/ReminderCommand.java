package command;

import java.time.LocalDate;

import storage.TaskList;
import task.Deadline;
import task.Event;
import task.Task;

/**
 * Command component that executes a reminder command.
 */
public class ReminderCommand extends Command {
    private LocalDate queryDate;

    /**
     * Constructor for a date query reminder command.
     *
     * @param queryDate the reference date to generate reminders
     */
    public ReminderCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    @Override
    public String run(TaskList taskList) {
        int count = 0;
        String res = "Here are your reminders:\n";
        for (Task task: taskList.indexTask()) {
            if (task instanceof Deadline && !task.getIsCompleted()) {
                count++;
                res += count + ". ";

                if (((Deadline) task).daysToDeadline(queryDate) > 0) {
                    res += ((Deadline) task).daysToDeadline(queryDate) + " more days to " + task.getTask() + "\n";
                } else if (((Deadline) task).daysToDeadline(queryDate) == 0) {
                    res += task.getTask() + " due TODAY!\n";
                } else {
                    res += task.getTask() + " late by " + -((Deadline) task).daysToDeadline(queryDate) + " days\n";
                }
            } else if (task instanceof Event && !task.getIsCompleted() && ((Event) task).daysToEvent(queryDate) >= 0) {
                count++;
                res += count + ". ";

                if (((Event) task).daysToEvent(queryDate) > 0) {
                    res += ((Event) task).daysToEvent(queryDate) + " more days to " + task.getTask() + "\n";
                } else {
                    res += task.getTask() + " is happening TODAY!\n";
                }
            }
        }

        if (count == 0) {
            return "There is no task!";
        }

        return res.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReminderCommand)) {
            return false;
        }

        ReminderCommand onCommand = (ReminderCommand) o;

        return queryDate.equals(onCommand.queryDate);
    }
}
