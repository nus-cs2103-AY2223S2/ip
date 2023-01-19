public class Event extends Task {

    // Attributes:
    protected String from_date;
    protected String to_date;

    // Constructor:
    public Event(String title, String from_date, String to_date) {
        super(title);
        this.from_date = from_date;
        this.to_date = to_date;
    }

    // Methods:
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from_date + " to: " + to_date + ")";
    }
}
