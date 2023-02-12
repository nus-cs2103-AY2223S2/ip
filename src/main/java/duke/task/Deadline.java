package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadlineDay;

    public Deadline(String desc, boolean done, LocalDateTime deadlineDay) {
        super(desc, done);
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
