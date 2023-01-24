public class Event extends Task{
    private String to;
    private String from;
    public Event(String taskText, String from, String to) {
        super(taskText);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    public String writeFile() {
        return String.format("E|%s|%s|%s|%s", this.getCurrentStatus(), this.getTaskText(), this.from, this.to);
    }
}
