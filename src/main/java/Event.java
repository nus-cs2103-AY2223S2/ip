public class Event extends Task {

    // Attributes:
    protected String fromDate;
    protected String toDate;

    // Constructor:
    public Event(String userInput) {
        super(userInput.substring(6, userInput.indexOf("/from ") - 1));
        this.fromDate = userInput.substring(userInput.indexOf("/from ") + 6, userInput.indexOf("/to ") - 1);
        this.toDate = userInput.substring(userInput.indexOf("/to ") + 4);
    }

    // Methods:
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
