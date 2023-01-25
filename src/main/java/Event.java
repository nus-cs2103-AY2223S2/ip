public class Event extends Tasks {
    private String start_Date;
    private String end_Date;
    public Event(String content, String start_Date, String end_Date) {
        super(content);
        this.start_Date = start_Date;
        this.end_Date = end_Date;
    }

    public Event(boolean isMarked, String content, String start_Date, String end_Date) {
        super(isMarked, content);
        this.start_Date = start_Date;
        this.end_Date = end_Date;
    }

    @Override
    public String addDivider() {
        String d = " | ";
        int marked = this.isMarked() ? 1 : 0;
        String timePeriod = start_Date + d + end_Date;
        return "E" + d + marked + d + this.get_content() + d + timePeriod;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + start_Date + " to: " + end_Date + ")";
    }
}
