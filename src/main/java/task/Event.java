package task;

public class Event extends Task {
    private final String from;
    private final String to;
    
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(Task task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }


    @Override
    public Event mark(boolean isDone) {
        return new Event(super.mark(isDone), this.from, this.to);
    }


    @Override
    public String toString() {
        return String.format("[D]%s (from: %s to: %s)",
            super.toString(),
            this.from,
            this.to
        );
    }
}
