public class Event extends Task {
    String tag = "[E]";
    protected String from;
    protected String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    //Override toString
}
