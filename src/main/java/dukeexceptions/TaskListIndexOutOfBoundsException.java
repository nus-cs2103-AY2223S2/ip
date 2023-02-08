package dukeexceptions;

public class TaskListIndexOutOfBoundsException extends DukeException{
    public TaskListIndexOutOfBoundsException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Sorry, the requested index is out of bounds of the TaskList" + getMessage();
    }
}
