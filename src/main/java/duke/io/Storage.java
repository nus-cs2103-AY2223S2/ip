package duke.io;

import duke.exception.DukeException;

/**
 * Represents a readable and writable storage.
 */
public interface Storage {
    /**
     * Returns true if the storage exist. Otherwise, returns false.
     *
     * @return True if the storage exist. False otherwise.
     * @throws DukeException Indicates failure to check for file.
     */
    boolean doesExist() throws DukeException;

    /**
     * Creates the storage.
     *
     * @throws DukeException Indicates failure to create storage.
     */
    void create() throws DukeException;

    /**
     * Returns the contents stored in the storage.
     *
     * @return The contents stored in the storage.
     * @throws DukeException Indicates failure to read from storage.
     */
    String read() throws DukeException;

    /**
     * Writes data to the storage.
     *
     * @param data The data to be written to the storage.
     * @throws DukeException Indicates failure to write to storage.
     */
    void write(String data) throws DukeException;
}
