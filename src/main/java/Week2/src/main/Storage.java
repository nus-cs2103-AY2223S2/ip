package Week2.src.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath = "";

    /**
     * Storage constructor
     * @param filePath Takes filePath to save or load from
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
    }

    /**
     * Load the file
     * @return returns filePath to use in other classes.
     */
    public String load() {
        return filePath;
    }

}
