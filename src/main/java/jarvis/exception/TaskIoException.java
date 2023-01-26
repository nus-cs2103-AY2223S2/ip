package jarvis.exception;

/**
 * Exception from saving tasks to or retrieving tasks from local storage.
 */
public class TaskIoException extends JarvisException {
    public TaskIoException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
