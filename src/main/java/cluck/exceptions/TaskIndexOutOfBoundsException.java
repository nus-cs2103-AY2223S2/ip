package cluck.exceptions;

public class TaskIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public TaskIndexOutOfBoundsException() {
        super("Index is out of range of Task list!");
    }
}
