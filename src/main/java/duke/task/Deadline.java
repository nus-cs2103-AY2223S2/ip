package duke.task;

public class Deadline extends Task {

    protected String by;


    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public Deadline(String taskName, String by, boolean isDone) {
        super(taskName);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String[] encode() {
        String[] res = new String[]{"D", this.getStatusIcon(), this.taskName, this.by};
        return res;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}