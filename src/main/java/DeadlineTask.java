public class DeadlineTask extends Task {
    private final String deadline;
    DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline.replace("/by", "by:");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + deadline + ")";
    }
}
