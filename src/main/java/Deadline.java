public class Deadline extends Task{
    private String deadline;

    Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    Deadline(String content, boolean done, String deadline) {
        super(content, done);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
