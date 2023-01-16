public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription();
    }
}
