package duke.exception;

/** A base class representing exceptions that may be thrown by Duke. */
public abstract class DukeException extends Exception {

    /**
     * Creates a new DukeException with a given message.
     * 
     * @param msg Additional information regarding the exception
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Gets the name of the current exception.
     * 
     * @return The name of the current exception
     */
    public abstract String getExceptionName();

    @Override
    public String toString() {
        String s = String.format("%s: %s", this.getExceptionName(), super.getMessage());
        return s;
    }
}
