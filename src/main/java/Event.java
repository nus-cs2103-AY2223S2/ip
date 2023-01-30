public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws DukeException{
        super(description);
        this.from = from;
        this.to = to;

        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new DukeException(" ☹ OOPS!!! The description or time/date of an event cannot be empty.\n");
        }
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, from, to);
    }
}