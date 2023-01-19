package jarvis.exception;

/**
 * Exception from saving tasks to or retrieving tasks from local storage.
 */
public class TaskIOException extends JarvisException {
    public TaskIOException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
