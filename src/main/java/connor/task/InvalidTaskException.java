package connor.task;

public class InvalidTaskException extends Exception{
    public InvalidTaskException() {
        super("INVALID TASK DESCRIPTION");
    }
}
