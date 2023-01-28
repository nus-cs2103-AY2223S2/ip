package duke.exceptions;

/**
 * Represents an exception thrown by Duke due to failure in loading task list state from memory.
 */
public class MemoryFailedException extends DukeException {

    public MemoryFailedException() {

    }

    /**
     * Returns a string describing the MemoryFailedException.
     *
     * @return String representation of the MemoryFailedException.
     */
    @Override
    public String toString() {
        return "There was an issue loading up memory. Loading empty task list instead!";
    }

}
