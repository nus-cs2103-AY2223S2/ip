public class Event extends Task{
    private String from;
    private String to;
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String toString() {
        return String.format("[E]%s (%s%s)", super.toString(), from, to);
    }
}
