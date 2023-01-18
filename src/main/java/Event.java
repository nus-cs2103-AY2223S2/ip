public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;

    }

    @Override
    public String getTaskInline() {
        return "[E]" + super.getTaskInline();
    }

    @Override
    public String getTaskInline(Integer index) {
        return index.toString() + ". [E]" + super.getTaskInline() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to " + this.end + ")";
    }
}
