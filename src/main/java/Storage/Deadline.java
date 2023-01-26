package storage;

public class Deadline extends Task {

    protected String deadline;

    public Deadline(String task, String by) {
        super(task);
        this.deadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + status() + getTask() + " (by: " + getDeadline() + ")";
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String saveFormat() {
        return "[D]" + status() + getTask() + " | " + getDeadline() + "\n";
     }
}
