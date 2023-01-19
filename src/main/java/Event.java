public class Event extends Task {

    private String start, end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        return String.format("[E][%s] %s (from: %s, to: %s)", doneString, this.getTitle(), this.start, this.end);
    }

}
