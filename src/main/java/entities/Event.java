package entities;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("E | 1 | %s | %s | %s\n", super.getDescription(), this.from, this.to);
        }
        return String.format("E | 0 | %s | %s | %s\n", super.getDescription(), this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from:%s to:%s)", super.toString(), this.from, this.to);
    }

}
