package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage to save and load tasks.
 */
public class Storage {

    /** File path of storage */
    private final String filePath;

    /**
     * Constructs a new storage
     *
     * @param filePath File path to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a scanner that reads from disk.
     *
     * @return Scanner to read from.
     * @throws FileNotFoundException if file is not found.
     */
    public Scanner getScanner() throws FileNotFoundException {
        return new Scanner(new File(filePath));
    }

    /**
     * Returns a file writer to write to disk.
     *
     * @return File writer to write to.
     * @throws IOException If an exception occurred when writing to disk.
     */
    public FileWriter getFileWriter() throws IOException {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            if (!parentDirectory.mkdirs()) {
                throw new IOException("Failed to create required directories");
            }
        }
        return new FileWriter(file);
    }
}
