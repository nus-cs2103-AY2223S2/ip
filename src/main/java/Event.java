public class Event extends Task{
    private String from;
    private String to;
    private String name;
    private boolean isDone;

    public Event(String name, String from, String to) {
        super(name, false);
        this.name = name;
        this.isDone = false;
        this.from = from;
        this.to = to;
    }

    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.name = name;
        this.isDone = isDone;
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    @Override
    public String getDescription() {
        return name + "|" + from + "|" + to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
