package task;

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

    @Override
    public String toDataString() {
        return "D | " + (this.isCompleted ? "1" : "0") + " | " + this.task + " | " + this.deadline;
    }
}
