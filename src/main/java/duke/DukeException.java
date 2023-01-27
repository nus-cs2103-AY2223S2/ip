package duke;

/**
 * Is an extension of Exception to indicate exceptions specific to duke
 * Reference:
 * <a href="https://www.baeldung.com/java-new-custom-exception">
 *     Baeldung - Create a Custom Exception in Java</a>
 */
public class DukeException extends Exception {

    /**
     * Constructs a new duke-specific exception with the specified detail message.
     *
     * @param errorMessage Details of the error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructs a new duke-specific exception with the specified detail message and cause.
     *
     * @param errorMessage Details of the error
     * @param err The cause of the error
     */
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
