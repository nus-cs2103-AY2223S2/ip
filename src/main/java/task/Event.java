package task;

public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String task, String startDate, String endDate) {
        super(task, false);
        this.endDate = endDate;
        this.startDate = startDate;
    }
    
    public Event(String task, boolean isCompleted, String startDate, String endDate) {
        super(task, isCompleted);
        this.endDate = endDate;
        this.startDate = startDate;
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public String getStatus() {
        return String.format("%s", isCompleted());
    }

    @Override
    public String getDescription() {
        return getTask()+ " | " + startDate + " | " + endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
