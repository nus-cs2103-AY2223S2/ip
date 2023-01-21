public class Event extends Task {

    private String start;
    private String end;

    public Event(String desc, boolean isDone, String start, String end) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
    }

    public String statusStringForFile() {
        return String.format("EVENT / %s / %s / %s", super.stringFormatForFile(), this.start.trim(), this.end.trim());
    }

    /**
     * returns the string of the event
     * @return the string of  the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start + "to:" + this.end + ")";
    }
}
