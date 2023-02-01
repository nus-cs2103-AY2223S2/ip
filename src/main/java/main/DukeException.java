package main;

/**
 * Class that takes in all exceptions from invalid user input.
 */
public class DukeException extends Exception {
    private final String message;

    /**
     * Constructs DukeException.
     *
     * @param message Represents why the exception is raised.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns why the exception is raised.
     *
     * @return Details of why the exception is raised.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
