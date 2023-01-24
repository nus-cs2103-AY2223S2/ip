package tasktypes;

public class Event extends Task {
    public String start;
    public String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        Task.numTask++;
    }

    @Override
    public String getSaveFormat() {
        String done;
        if (this.done) {
            done = "1";
        } else {
            done = "0";
        }
        return "E" + ",," + done + ",," + this.description + ",," + start + ",," + end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + ", " + "to: " + this.end + ")";
    }
}