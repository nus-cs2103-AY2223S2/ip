package duke;

public class Todo extends Task {

    public Todo(String description, boolean isDone, String taskType) {
        super(description, isDone, taskType);
    }

    public Todo(String description) {
        this(description, false, "T");
    }

    public Task markTask() throws DukeException {
        if (super.isDone) {
            throw new DukeException("This task is already marked!");
        }
        return new Todo(super.description, true, super.taskType);
    }

    public Task unmarkTask() throws DukeException {
        if (!super.isDone) {
            throw new DukeException("This task is already unmarked!");
        }
        return new Todo(this.description, false, super.taskType);
    }

    public String formatTask() {
        return String.format("T|%b|%s", this.isDone, this.description);
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", super.getTaskTypeBox(), super.getStatusCheckbox(), super.toString());
    }
}
