public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("☹ OOPS!!! The requested task was not found in the task list.");
    }
}
