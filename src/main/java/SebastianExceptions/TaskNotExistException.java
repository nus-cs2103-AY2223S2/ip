package SebastianExceptions;

public class TaskNotExistException extends IndexOutOfBoundsException{
    public TaskNotExistException() {
        super("Sorry, the task does not exist");
    }
}
