package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String getTaskSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getTaskSymbol() + "]" + super.toString()+ " (at: " + at + ")";
    }
}