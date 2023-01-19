/**
 * The Todo task.
 * Inherits from the superclass Task.
 */
public class Todo extends Task {

    public Todo(String nameOfTask, boolean isDone) {
        super(nameOfTask, isDone);
    }

    public Todo(String nameOfTask) throws DukeException {
        super(nameOfTask);
        if (nameOfTask.isBlank()) {
            throw new DukeException("You cannot have an empty todo task.");
        }
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }
}
