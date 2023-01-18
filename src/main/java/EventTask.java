public class EventTask extends Task {

    protected String by;
    protected String byy;

    public EventTask(String description, String by, String byy) {
        super(description);
        this.by = by;
        this.byy = byy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + by + " to: " + byy + ")";
    }
}