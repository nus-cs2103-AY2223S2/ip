package task;

public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String task, String startTime, String endTime) {
        this.task = task;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String content = this.task + " (from: " + this.startTime + " to: " + this.endTime + ")";
        if (isCompleted) {
            return "[E][X] " + content;
        } else {
            return "[E][ ] " + content;
        }
    }
}
