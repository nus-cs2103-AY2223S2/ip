package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.ui.Ui;

/**
 * Reminder class to send reminders about any upcoming deadlines.
 */
public class Reminder {
    private LocalDateTime timeToSendReminders;

    public Reminder(LocalDateTime now) {
        timeToSendReminders = now.plusHours(24);
    }

    public String getReminder(TaskList tasks, Ui ui) {
        ArrayList<Deadline> upcomingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getLength(); i++) {
            Task currTask = tasks.getTask(i);
            if (currTask.getTaskType().equals("T") || currTask.getTaskType().equals("E")) {
                continue;
            } else if (currTask.getTaskType().equals("D")) {
                Deadline dTask = (Deadline) currTask;
                LocalDateTime checkDiff = dTask.getDeadline();
                if (checkDiff.isBefore(timeToSendReminders) && checkDiff.isAfter(LocalDateTime.now())) {
                    upcomingTasks.add((Deadline) currTask);
                } else {
                    continue;
                }
            }
        }

        return ui.showUpcomingTasks(upcomingTasks);
    }


}
