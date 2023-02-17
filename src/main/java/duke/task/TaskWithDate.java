package duke.task;

import java.time.LocalDateTime;

/**
 * TaskWithDate with additional dateForComparison attribute.
 */
public abstract class TaskWithDate extends Task {
    private LocalDateTime dateForComparison;

    /**
     * Constructor for TaskWithDate.
     * @param desc
     * @param type
     * @param dateForComparison
     */
    public TaskWithDate(String desc, String type, LocalDateTime dateForComparison) {
        super(desc, type);
        this.dateForComparison = dateForComparison;
    }

    public LocalDateTime getDateForComparison() {
        return dateForComparison;
    }
}
