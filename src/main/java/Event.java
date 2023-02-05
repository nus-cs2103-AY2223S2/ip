public class Event extends Task {
    private String from;
    private String to;
    public Event(String cmd) {
        this(cmd.split(" /")[0],
                cmd.split(" /")[1].replace("from ", ""),
                cmd.split(" /")[2].replace("to ", ""));
    }
    Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }
    /**
     * Return the String for Event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
