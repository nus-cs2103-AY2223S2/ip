public class DeadlineTask extends Task {
    private final String deadline;
    DeadlineTask(String name, String deadline) throws DukeException {
        this(argumentCheck(deadline), name, deadline);
    }

    private DeadlineTask(Void ignored, String name, String deadline) throws DukeException {
        super(name);
        this.deadline = deadline.replace("/by", "by:");
    }

    private static Void argumentCheck(String deadline) throws DukeException {
        if (deadline.isEmpty()) {
            throw new DukeException("Empty deadline for deadline task");
        }
        return null;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + deadline + ")";
    }
}
