package Storage;

public class Deadline extends Task {

    protected String deadline;

    public Deadline(String task, String by) {
        super(task);
        this.deadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + status() + getTask() + getDeadline();
    }

     private String getDeadline() {
        return " (by: " + this.deadline + ")";
     }
}
