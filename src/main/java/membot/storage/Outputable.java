package membot.storage;

import java.io.IOException;

/**
 * An interface for outputting data to a storage.
 */
public interface Outputable {
    /**
     * Outputs data to a storage, such as a database or a file.
     *
     * @param data Data to be outputted.
     * @throws IOException If an error occurs when writing to a storage.
     */
    void write(String data) throws IOException;
}
