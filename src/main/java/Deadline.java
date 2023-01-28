import java.time.LocalDate;

public class Deadline extends Task {

    private LocalDate endTime;

    public Deadline(String s, String endTime) {
        super(s);
        this.endTime = LocalDate.parse(endTime);
    }

    public Deadline(Boolean isTaskDone, String taskDetails, String taskDate) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
        this.endTime = LocalDate.parse(taskDate);
    }

    @Override
    public String toString() {
        if (!taskDone) {
            return "[D][ ] " + this.taskName + " (by: " + this.endTime + ")";
        }
        return "[D][X] " + this.taskName + " (by: " + this.endTime + ")";
    }

}
