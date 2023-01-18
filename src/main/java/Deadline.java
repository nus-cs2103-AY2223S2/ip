public class Deadline extends Task{
    private String deadline;

    Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
