public class Event extends Task {

    protected String duration;

    public Event(String description, String by) {
        super(description);
        this.duration = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (%s)", duration);
    }
}
