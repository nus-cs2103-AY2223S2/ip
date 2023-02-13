package duke.reminder;

import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reminder {
    private LocalDateTime dueDateTime;
    private Task task;

    public Reminder(LocalDateTime dueDateTime, Task t) {
        this.dueDateTime = dueDateTime;
        this.task = t;
    }
    public LocalDateTime getLocalDateTime() {
        return dueDateTime;
    }
    public boolean isToday() {
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDate remindDay = dueDateTime.toLocalDate();
        return now.isEqual(remindDay);
    }
    @Override
    public String toString() {
        return "Reminder on: " + dueDateTime;
    }
}
