public class Event extends Task {
    String startTime;
    String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startTime + endTime + ")";
    }
}
