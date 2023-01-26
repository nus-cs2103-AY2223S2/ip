/**
 * Given a task and a time range, constructs a Task event.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */

public class TaskEvent extends Task{
    protected String from;
    protected String to;

    public TaskEvent(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (%s%s)", this.from, this.to);
    }

    @Override
    public String toStringDb() {
        return String.format(
                "E|%s|%s|%s",
                super.toStringDb(),
                this.from, this.to
        );
    }
}