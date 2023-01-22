public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws DukeException{
        super(description);
        this.from = from;
        this.to = to;
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}
