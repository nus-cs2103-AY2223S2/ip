package duke.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.task.Task;

/**
 * Encapsulates a reminder about an event or deadline.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class Reminder {
    private LocalDateTime dueDateTime;
    private Task task;

    /**
     * Constructor for a <code>Reminder</code>.
     * @param dueDateTime The date and time to show reminder.
     * @param t <code>Task</code> this reminder is for.
     */
    public Reminder(LocalDateTime dueDateTime, Task t) {
        this.dueDateTime = dueDateTime;
        this.task = t;
    }
    public LocalDateTime getLocalDateTime() {
        return dueDateTime;
    }

    /**
     * Returns whether the reminder should be shown today.
     * @return <code>True</code> if reminder is set for today.
     */
    public boolean shouldRemindToday() {
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDate remindDay = dueDateTime.toLocalDate();
        boolean isDueOrOverdue = now.isEqual(remindDay) || now.isAfter(remindDay);
        return isDueOrOverdue;
    }
    @Override
    public String toString() {
        return "Reminder on: " + dueDateTime;
    }
}
