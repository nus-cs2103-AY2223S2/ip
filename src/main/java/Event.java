public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, boolean status, String from, String to) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public String fileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + from + " | " + to + "\n";
    }

    private String getEventInterval() {
        return " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription() + this.getEventInterval();
    }
}
