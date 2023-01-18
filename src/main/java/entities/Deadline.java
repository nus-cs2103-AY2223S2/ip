package entities;

import enums.TaskType;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public SerializableTask serialize() {
        String flags = by;
        return new SerializableTask(TaskType.DEADLINE, isDone, description, flags);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
