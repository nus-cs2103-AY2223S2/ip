package duke;

/**
 * Represents an Exception due to absence of description following task.
 */
public class EmptyDescription extends Exception {
    public EmptyDescription(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!!" + getMessage();
    }
}
