package duke;
public class Event extends Task {

    private final String FROM;
    private final String TO;

    public Event(String description, String from, String to) throws EmptyDescriptionException{
        super(description);
        this.FROM = from;
        this.TO = to;

    }
    @Override
    public  String getFileDescription() {
        return "E | " + super.getStatusIcon() + " | "
                + super.description + " | "  + this.FROM + " | " + this.TO;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + FROM + "to: " + TO + ")";
    }
}

