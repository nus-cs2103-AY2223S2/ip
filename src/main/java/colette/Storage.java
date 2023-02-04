package colette;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import colette.exception.ColetteException;

/** Class that saves to and loads from task storage */
public class Storage {

    /** Relative path to the data directory used for storing tasks */
    private String dirPath;
    /** Name of file used for storing tasks */
    private final String fileName = "colette.txt";

    /**
     * Constructs a Storage object with a specified path
     * to the data directory used for storing tasks.
     *
     * @param dirPath Relative path to the data directory
     *                used for storing tasks.
     */
    public Storage(String dirPath) {
        this.dirPath = dirPath;
    }

    /**
     * Loads tasks from storage.
     *
     * @return String representing all stored tasks
     * @throws ColetteException if loading from storage fails.
     */
    public String load() throws ColetteException {
        try {
            Path dirPath = Paths.get(this.dirPath);
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            if (!Files.exists(Paths.get(this.dirPath + this.fileName))) {
                File f = new File(this.dirPath + this.fileName);
                f.createNewFile();
            }
            return Files.readString(Paths.get(this.dirPath + this.fileName));
        } catch (IOException e) {
            throw new ColetteException("I couldn't read from storage. There was a rock blocking the way...");
        }
    }

    /**
     * Saves tasks to storage.
     *
     * @param taskList String representing all tasks to save
     * @throws ColetteException if saving to storage fails.
     */
    public void save(String taskList) throws ColetteException {
        try {
            FileWriter fw = new FileWriter(this.dirPath + this.fileName);
            fw.write(taskList);
            fw.close();
        } catch (IOException e) {
            throw new ColetteException("I couldn't write to storage. I tripped past the entranceway.");
        }
    }

}
