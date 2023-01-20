public class Event extends Task{
    private final String from, to;

    public Event(String str, String from, String to) {
        super(str);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
