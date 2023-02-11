package duke.task;

import java.time.LocalDateTime;
public abstract class TaskWithDate extends Task {
    private LocalDateTime dateForComparison;
    public TaskWithDate(String desc, String type, LocalDateTime dateForComparison) {
        super(desc, type);
        this.dateForComparison = dateForComparison;
    }

    public LocalDateTime getDateForComparison() {
        return dateForComparison;
    }
}
