public class Event extends Task {
    protected String by;
    protected String from;
    protected String to;

    protected boolean isDone;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
    public String saveFormat(){
        return String.format("E | %s |  %s to %s", super.saveFormat(), this.from, this.to);
    }
}
