package rick.exceptions;

/**
 * Represents the exception indicating that an invalid index was provided to
 * access the database.
 *
 * @author SeeuSim
 *         AY22/23-S2 CS2103T
 */
public class TaskListInvalidIndexException extends RickException {
    /**
     * Constructs and returns an Exception indicating the provided index was
     * invalid.
     *
     * @param idx The index provided to index the storage.
     */
    public TaskListInvalidIndexException(int idx) {
        super(String.format(
                "An invalid index `%s` was provided. Ensure a proper index is provided.",
                idx
        ));
    }
}
