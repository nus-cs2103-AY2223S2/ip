public class Event extends Task {
    private String desc;
    private String from;
    private String to;

    public Event(int id, String description, String from, String to) {
        super(id);
        desc = description;
        this.from = from;
        this.to = to;
    }

    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        String isDone = isCompleted() ? "X" : " ";
        return String.format("[E][%s] %s (from: %s to %s)", isDone,description(),  from, to);
    }
}
