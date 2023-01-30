package domain.entities.core;

/**
 * The interface for things that can be read from. This can be used to wrap
 * around files, System.in, etc.
 */
public interface StringReadable {
    /**
     * Read the next line from the interface. Will employ a blocking read.
     *
     * @return the next line from the interface.
     */
    String nextLine();

    /**
     * Check if there is a next line to be read.
     *
     * @return true if there is a next line to be read, false otherwise.
     */
    boolean hasNextLine();
}
