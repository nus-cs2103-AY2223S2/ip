package exception;

public class TaskIOException extends JarvisException {
    public TaskIOException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
