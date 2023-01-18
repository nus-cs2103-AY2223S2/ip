public class Event extends Task{
    protected String from;
    protected String to;
    protected String taskType;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }

    public String toString() {
        return "[" + this.taskType + "][" + super.status + "] "
                + super.description
                + "(from: " + this.from
                + "to: " + this.to
                + ")";
    }
}
