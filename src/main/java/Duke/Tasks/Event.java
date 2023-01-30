package Duke.Tasks;

public class Event extends Task {
    protected String startingTime;
    protected String endingTime;
    public Event(String description, String startingTime, String endingTime) {
        super(description);
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    @Override
    public String printTask() {
        return String.format("E | %d | %s | %s-%s ", isComplete ? 1 : 0, description, startingTime, endingTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.startingTime + " to: " + endingTime + ")";
    }
}
