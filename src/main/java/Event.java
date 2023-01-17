public class Event extends Task {
    private String start;
    private String end;
    public Event(String str, boolean checked) {
        super(str, checked);
    }

    public void setStartEnd(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
