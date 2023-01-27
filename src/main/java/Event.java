public class Event extends Task {
    private final String startDateTime;
    private final String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

    @Override
    public String getTaskState() {
        return "E | " + super.getTaskString() + " | "
                + this.startDateTime + " | " + this.endDateTime;
    }
}
