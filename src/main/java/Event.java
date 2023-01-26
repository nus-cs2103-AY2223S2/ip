public class Event extends Task{
    private String timeOfEvent;

    public Event(String description, String timeOfEvent) {
        super(description);
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeOfEvent + ")";
    }

    @Override
    public String sendOutputToFile() {
        return String.format("E | %d | %s | %s" , isDone ? 1 : 0, description, timeOfEvent);
    }
}
