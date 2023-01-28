public class Deadline extends Task{
    private final String dueAt;

    public Deadline(String task, String dueAt) {
        super(task);
        this.dueAt = dueAt;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + dueAt;
    }

    @Override
    public String toCommand() {
        return "deadline " + super.description + " /by " + dueAt + (super.isDone ? "\nmark last": "");
    }
}
