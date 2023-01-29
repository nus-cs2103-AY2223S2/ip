public class DeadlineTask extends Task {
    private String by;
    public DeadlineTask(String title, String by) {
        super(title);
        this.by = by;
    }

    public String save() {
        String status = this.isDone ? "DONE " : "NOTDONE ";
        return "DEADLINE " + status + this.by + "\n";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[D]" + status + this.title + " (by: " + this.by + ")";
    }
}
