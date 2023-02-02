public class Event extends Task {
    private static final String taskType = "[E]";
    private String from;
    private String to;

    public Event(String description, String from, String to) throws MissingDescriptionException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (from: " + from + " to:" + to + ")";
    }

    @Override
    public String toStorageData() {
        String completed = getStatusIcon();
        return taskType + "//" + completed + "//" + description + "//" + from + "//" + to;
    }
}
