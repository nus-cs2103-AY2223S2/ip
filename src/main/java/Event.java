public class Event extends Task{
    private String start;
    private String end;

    Event(String content, String start, String end) {
        super(content);
        this.start = start;
        this.end = end;
    }

    Event(String content, boolean done, String start, String end) {
        super(content, done);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
