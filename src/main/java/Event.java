public class Event extends Task{
    private String timeFrom;
    private String timeTo;

    public Event(String description, String timeFrom, String timeTo) {
        super(description);
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + timeFrom + "to: " + timeTo + ")";
    }
}
