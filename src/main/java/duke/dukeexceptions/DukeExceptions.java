package duke.dukeexceptions;

/**
 * Exceptions by Duke program.
 */
public class DukeExceptions extends RuntimeException {
    public DukeExceptions(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
