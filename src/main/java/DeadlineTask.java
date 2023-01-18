public class DeadlineTask extends Task {
    private String by;
    public DeadlineTask(String title, String by) {
        super(title);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[D]" + status + this.title + " (by: " + this.by + ")";
    }
}
