package chungus;

/**
 * An exception related to the Chungus app.
 */
class ChungusException extends RuntimeException {
    public ChungusException(String msg) {
        super(msg);
    }

    public ChungusException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

/**
 * An exception to represent whenever a requested task could not be found.
 */
class TaskNotFoundException extends ChungusException {
    public TaskNotFoundException(int idx) {
        super(String.format("Task %d does not exist", idx));
    }
}

/**
 * An exception to represent when the serialization of a task is invalid.
 */
class TaskMarshalException extends ChungusException {
    public TaskMarshalException(String s) {
        super(String.format("Bad marshal format: \"%s\"", s));
    }
}
