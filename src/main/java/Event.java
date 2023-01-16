public class Event extends Task{
    protected String by,from,to;

    public Event(String TaskName, String from, String to) {
        super(TaskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatus() + "] " + super.toString()
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}