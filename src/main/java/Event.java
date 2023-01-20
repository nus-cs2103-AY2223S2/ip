public class Event extends Task {
    private String startDateTime;
    private String endDateTime;
    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String saveString() {
        return "E;" + this.isDone + ";" + this.description + ";" + this.startDateTime + ";"
                + this.endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDateTime + "to: " + endDateTime + ")";
    }
}
