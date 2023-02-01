package duke;

public class Event extends Task {
    String startTime;
    String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = Parser.formatDateString(startTime);
        this.endTime = Parser.formatDateString(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(From: " + startTime + "hrs" + " To: " +  endTime + "hrs" + ")";
    }

    public String getSaveString() {
        return String.format("event / %s / %s / %s / %s", super.getSaveString(), this.getDescription(), startTime, endTime);
    }
}
