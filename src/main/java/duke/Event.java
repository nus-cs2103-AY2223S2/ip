package duke;
public class Event extends Task {

    private final String from;
    private final String to;

    public Event(String description, String from, String to) throws EmptyDescriptionException{
        super(description);
        this.from = from;
        this.to = to;

    }
    @Override
    public  String getFileDescription() {
        return "E | " + super.getStatusIcon() + " | "
                + super.description + " | "  + this.from + " | " + this.to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}

