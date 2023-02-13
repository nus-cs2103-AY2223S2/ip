package duke.dukeexceptions;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Task not found...");
    }
}
