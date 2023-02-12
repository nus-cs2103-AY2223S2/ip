package duke.task;

import java.time.LocalDateTime;

/**
 * This class creates a Deadline object which inherits from Task
 * Deadline (String description, boolean isDone, LocalDateTime deadlineDay)
 *
 * @author He Shuimei
 * @version 0
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDay;

    public Deadline(String desc, boolean isDone, LocalDateTime deadlineDay) {
        super(desc, isDone);
        this.deadlineDay = deadlineDay;
    }

    /**
     * getter for deadline day
     * @return this.deadlineDay
     */
    public String getDeadlineDay(){
        return format24HrDate(this.deadlineDay);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + formatDate(this.deadlineDay) + ")";
    }
}
