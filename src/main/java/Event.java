public class Event extends Tasks {
    private String start_Date;
    private String end_Date;
    public Event(String content, String start_Date, String end_Date) {
        super(content);
        this.start_Date = start_Date;
        this.end_Date = end_Date;
    }

    public String toString() {
        return "[E] " + super.toString() + "(from: " + start_Date + " to: " + end_Date + ")";
    }
}
