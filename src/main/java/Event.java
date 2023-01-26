public class Event extends Task {
    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSave() {
        if (super.isDone()) {
            return "D | 1 | " + super.getName() + " | " + this.start + " | " + this.end + "\n";
        } else {
            return "D | 0 | " + super.getName() + " | " + this.start + " | " + this.end + "\n";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
