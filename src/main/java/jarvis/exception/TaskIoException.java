package jarvis.exception;

/**
 * Exception from saving tasks to or retrieving tasks from local storage.
 */
public class TaskIoException extends JarvisException {
    public TaskIoException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }

    public TaskIoException(String message) {
        super(message, "I'm afraid the tasks could not be saved.");
    }
}
