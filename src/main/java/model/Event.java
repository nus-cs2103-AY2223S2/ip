package model;

public class Event extends Task {
    public static final String TAG = "[E]";

    private String start;
    private String end;

    public Event(String title, String start, String end) {
        super(title);

        this.start = start;
        this.end = end;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public String getDeadline() {
        return Task.EMPTY;
    }

    @Override
    public String getStartDateTime() {
        return this.start;
    }

    @Override
    public String getEndDateTime() {
        return this.end;
    }

    @Override
    public String toString() {
        return Event.TAG + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
