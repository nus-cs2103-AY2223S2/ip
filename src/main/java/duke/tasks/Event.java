package duke.tasks;

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

    @Override
    public String serialize() {
        String isDone = isCompleted() ? "1" : "0";
        return String.format("E|%s|%s|%s|%s|%s", id(), isDone, description(), from, to);
    }

    
    public static Event deserialize(String s) {
        String[] parts = s.split("\\|");
        Event event = new Event(Integer.parseInt(parts[1]), parts[3], parts[4], parts[5]);
        if (parts[2].equals("1")) {
            event.markCompleted();
        }
        return event;
    }
}
