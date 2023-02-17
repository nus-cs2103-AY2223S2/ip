package Duke.Exceptions;

/**
 * Exceptions when user inputs find command without
 * specific content.
 */
public class EmptyFind extends DukeException {
    private String emptyFind;

    /**
   * Constructor for Exception when cannot find matching task.
   */
    public EmptyFind() {
        this.emptyFind = "I'm sorry. The description of a find"
            + " cannot be empty.";
    }

    @Override
    public String toString() {
        return emptyFind;
    }
}
