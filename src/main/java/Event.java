public class Event extends Task {
    String from;
    String to;

    Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from + " | " + to;
    }

}
