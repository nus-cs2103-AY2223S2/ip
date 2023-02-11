package Week2.src.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
<<<<<<< HEAD
 * Stores data from the user and write it in the file,
 * or load the data from given file.
=======
 * Deals with loading tasks from the file and saving tasks in the file
>>>>>>> branch-A-JavaDoc
 */
public class Storage {
    private String filePath = "";

    /**
     * Storage Constructor.
     * It contains the file path to store or load data.
     * @param filePath
     * Storage constructor
     * @param filePath Takes filePath to save or load from
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
    }

    /**
     * Returns file path to load data.
     * @return
     * Load the file
     * @return returns filePath to use in other classes.
     */
    public String load() {
        return filePath;
    }

}
