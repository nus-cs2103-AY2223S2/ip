public class Deadline extends Task{
    private String deadline;

    public Deadline(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String content = this.task + " (by: " + this.deadline + ")";
        if (isCompleted) {
            return "[D][X] " + content;
        } else {
            return "[D][ ] " + content;
        }
    }
}
