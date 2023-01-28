import java.time.LocalDate;

public class Event extends Task {

    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String s, String startTime, String endTime) {
        super(s);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    public Event(Boolean isTaskDone, String taskDetails, String taskDate) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
        this.startTime = LocalDate.parse(taskDate);
    }

    @Override
    public String toString() {
        if (!taskDone) {
            return "[E][ ] " + this.taskName
                + " (from: " + this.startTime + " to: " + this.endTime + ")";
        }
        return "[E][X] " + this.taskName 
            + " (from: " + this.startTime + " to: " + this.endTime;
    }

}
