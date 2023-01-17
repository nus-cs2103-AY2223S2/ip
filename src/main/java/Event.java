public class Event extends Task {

    private String start;
    private String end;

    public Event(String command, String start, String end) {
        super(command);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + "to:" + end + ")";
    }
}
