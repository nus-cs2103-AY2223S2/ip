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

    @Override
    public String toString() {
        String str = this.getStr();
        boolean checked = this.isChecked();
        String startTime = this.getStart();
        String endTime = this.getEnd();
        if (checked) {
            return "[E][X] " + str + " (from: " + startTime +
                    " to: " + endTime + ")";
        } else {
            return "[E][ ] " + str + " (from: " + startTime +
                    " to: " + endTime + ")";
        }
    }
}
