public class Event extends Task {
    protected String begin;
    protected String end;

    public Event(String description, String begin, String end) {
        super(description);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.begin + "to:" + this.end + ")";
    }
}
