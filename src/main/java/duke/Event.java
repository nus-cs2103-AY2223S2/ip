package duke;

public class Event extends Task {
    String startTime;
    String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = Parser.parseDate(startTime);
        this.endTime = Parser.parseDate(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(From: " + startTime + "hrs" + " To: " +  endTime + "hrs" + ")";
    }

    public String saveString() {
        return String.format("event / %s / %s / %s / %s", super.saveString(), this.getDescription(), startTime, endTime);
    }
}
