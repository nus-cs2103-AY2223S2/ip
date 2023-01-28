public class Event extends Task {

    private String startTime;
    private String endTime;

    public Event(String s, String startTime, String endTime) {
        super(s);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(Boolean isTaskDone, String taskDetails, String taskDate) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
        this.startTime = taskDate;
        this.endTime = taskDate;
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
