package duke.task;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline) {
        this(name, false, deadline);
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    public String serialize() {
        String serialized = String.format("%s|%s", super.serialize(), this.deadline);
        return serialized;
    }

    @Override
    public String toString() {
        String s = String.format("%s (by: %s)", super.toString(), this.deadline);
        return s;
    }
}
