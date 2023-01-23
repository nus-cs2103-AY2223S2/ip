package Week2.src.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Stores data from the user and write it in the file,
 * or load the data from given file.
 */
public class Storage {
    private String filePath = "";

    /**
     * Storage Constructor.
     * It contains the file path to store or load data.
     * @param filePath
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
    }

    /**
     * Returns file path to load data.
     * @return
     */
    public String load() {
        return filePath;
    }

}
