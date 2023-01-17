public class TaskEvent extends DukeTask{
    private String from;
    private String to;
    public TaskEvent(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)");
    }
}
