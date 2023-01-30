package tasks;

public class Deadline extends Task {

    protected String by;
    private static final String tag = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String saveTask() {
        String completed = this.isDone? "1":"0";
        return this.tag + " | " + completed + " | "
                + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")" +"\n";
    }
}