package duke.task;

/**
 * Todo task representation.
 */
public class ToDo extends Task {
    public ToDo(String description, String place) {
        super(description, place, TaskType.TODO);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
