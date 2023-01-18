public class Deadline extends Task{
    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String s;
        if (this.completed) {
            s = "[D][X] " + this.taskName + " (by: " + this.deadline + ")";
        } else {
            s = "[D][ ] " + this.taskName  + " (by: " + this.deadline + ")";
        }
        return s;
    }
}
