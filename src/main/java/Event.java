public class Event extends Task{
    protected String from,to;

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

    @Override
    public String toSaveString() {
        return "E" + "=" + super.getStatus() + "=" + super.toSaveString().strip()
                + "=" + this.from + "=" + this.to;
    }
}