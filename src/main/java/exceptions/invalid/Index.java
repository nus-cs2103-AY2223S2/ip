package exceptions.invalid;

/**
 * Thrown in place of ArrayIndexOutOfBoundsException.
 */
public class Index extends exceptions.DukeException {
    /**
     * Constructs an Invalid Index Exception for the given index.
     *
     * Example:
     * User tries to delete entry `9` when the task list is empty.
     *
     * @param ind User supplied input.
     */
    public Index(int ind) {
        super(String.format("%s The list does not contain index %d", OOPS, ind));
    }
}
