public class Deadline extends Task{
    private final String dueAt;
    public Task.Type type;

    public Deadline(String task, String dueAt) {
        super(task);
        this.dueAt = dueAt;
        type = Type.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + dueAt;
    }

    @Override
    public String toCommand() {
        return "deadline " + super.taskStr + " /by " + dueAt + (super.done ? "\nmark last": "");
    }
}
