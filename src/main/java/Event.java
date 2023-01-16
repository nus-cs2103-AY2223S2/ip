public class Event extends Task {
    String from;
    String to;

    @Override
    protected String getType() {
        return "[E]";
    }

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (From%sto%s)", getType(), getStatusIcon(), this.desc, this.from, this.to);

    }
}
