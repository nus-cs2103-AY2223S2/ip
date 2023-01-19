package duke.io;

import duke.exception.DukeException;

/**
 * Represents a readable and writable storage.
 */
public interface Storage {
    /**
     * Returns true if the storage exist, otherwise, false.
     *
     * @return True if the storage exist, otherwise, false.
     * @throws DukeException Indicates failure to check for file.
     */
    boolean doesExist() throws DukeException;

    /**
     * Create the storage.
     *
     * @throws DukeException Indicates failure to create storage.
     */
    void create() throws DukeException;

    /**
     * Return the contents stored in the storage.
     *
     * @return The contents stored in the storage.
     * @throws DukeException Indicates failure to read from storage.
     */
    String read() throws DukeException;

    /**
     * Write data to the storage.
     *
     * @param data The data to be written to the storage.
     * @throws DukeException Indicates failure to write to storage.
     */
    void write(String data) throws DukeException;
}
