public class Deadline extends Task {
    private String deadline;

    public Deadline(String command, String deadline) {
        super(command);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadline + ")";
    }
}
