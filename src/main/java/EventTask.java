public class EventTask extends Task {
    String from;
    String to;
    public EventTask(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[E]" + status + this.title + " (from: " + this.from + " to: " + this.to + ")";
    }
}
