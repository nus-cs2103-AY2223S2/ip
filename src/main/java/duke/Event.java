package duke;

public class Event extends Task {
    private static final String TASK_TYPE = "[E]";
    private String from;
    private String to;

    public Event(String description, String from, String to) throws MissingDescriptionException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return TASK_TYPE + super.toString() + " (from: " + from + " to:" + to + ")";
    }

    @Override
    public String toStorageData() {
        String completionStatus = getStatusIcon();
        return TASK_TYPE + "//" + completionStatus + "//" + description + "//" + from + "//" + to;
    }
}
