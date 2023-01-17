package task;

public class Deadline extends Task {
    private final String by;


    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(Task task, String by) {
        super(task);
        this.by = by;
    }


    @Override
    public Deadline mark(boolean isDone) {
        return new Deadline(super.mark(isDone), this.by);
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
