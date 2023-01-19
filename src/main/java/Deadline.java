public class Deadline extends Tasks {

    private String deadline;

    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;

    }

    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.deadline + ")";
    }
}
