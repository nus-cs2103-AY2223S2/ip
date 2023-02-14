package duke;

public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = Parser.formatDateString(startTime);
        this.endTime = Parser.formatDateString(endTime);
    }

    public String getStartTime() {
        return startTime;
    }

    /**
     * Creates a string representation of the event.
     * @return String object of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(From: " + startTime + "hrs" + " To: " +  endTime + "hrs" + ")";
    }

    /**
     * Creates the string representation of the format used to save the tasks.
     * @return String object of the saved task.
     */
    public String getSaveString() {
        return String.format("event / %s / %s / %s / %s", super.getSaveString(), this.getDescription(), startTime, endTime);
    }
}
