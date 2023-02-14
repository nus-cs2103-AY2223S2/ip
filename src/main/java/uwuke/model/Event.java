package uwuke.model;

public class Event extends Task {

    private String start;
    private String end;

    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("%s%s (from: %s to: %s)", 
            "[E]", super.toString(), start, end);
    }
    
}
