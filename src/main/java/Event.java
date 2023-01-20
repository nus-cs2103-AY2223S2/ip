public class Event extends Task {

    // Attributes:
    protected String from_date;
    protected String to_date;

    // Constructor:
    public Event(String user_input) {
        super(user_input.substring(6, user_input.indexOf("/from ") - 1));
        this.from_date = user_input.substring(user_input.indexOf("/from ") + 6, user_input.indexOf("/to ") - 1);
        this.to_date = user_input.substring(user_input.indexOf("/to ") + 4);
    }

    // Methods:
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from_date + " to: " + to_date + ")";
    }
}
