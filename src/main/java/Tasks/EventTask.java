package Tasks;

import java.time.LocalDateTime;

public class EventTask extends Task {
    LocalDateTime dueDate;
    LocalDateTime startDate;

    public EventTask(String taskName, LocalDateTime startDate, LocalDateTime dueDate) {
        super(taskName);
        this.dueDate = dueDate;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + displayLocalDate(startDate) + " to: " + displayLocalDate(dueDate) + ")";
    }
}
