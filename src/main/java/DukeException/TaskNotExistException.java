package DukeException;

public class TaskNotExistException extends DukeException {
    public TaskNotExistException() {}

    @Override
    public String toString() {
        return "Sorry, There is no such task for this command\n";
    }
}
