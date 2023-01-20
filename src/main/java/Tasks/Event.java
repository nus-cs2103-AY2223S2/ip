package Tasks;

public class Event extends Task {
    protected String startingTime;
    protected String endingTime;
    public Event(String description, String startingTime, String endingTime) {
        super(description);
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.startingTime + " to: " + endingTime + ")";
    }
}
