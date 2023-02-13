package duke;

public class Event extends Task {

    String From;
    String To;
    public Event(String description, String From, String To) {
        super(description);
        this.From = From;
        this.To = To;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.From + " to: " + this.To + ")";

    }
}
