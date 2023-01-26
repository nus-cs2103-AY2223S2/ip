import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A class to handle storing and loading of task list data.
 */
public class Storage {
    /** Path of file to store list data */
    private String filePath;

    /**
     * Constructor for Storage class.
     * @param filePath Path to file to store list data (no extension).
     * @throws DukeException If filePath ends with an extension.
     */
    public Storage(String filePath) throws DukeException{
        // Code adapted from https://www.baeldung.com/java-file-extension to get file extension.
        String extension = Optional.ofNullable(filePath)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filePath.lastIndexOf(".") + 1)).toString();
        if (!extension.equals("Optional.empty")) {
            throw new DukeException("Invalid file path. Please provide file without any extension.");
        } else {
            this.filePath = filePath;
        }
    }

    /**
     * Saves the current taskList on file.
     * @throws DukeException If data could not be saved.
     */
    public void save(TaskList list) throws DukeException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException fe) {
            throw new DukeException("Could not save file.");
        }
    }

    /**
     * Loads data from the file path (provided in constructor).
     * @return The ArrayList of Tasks, if found.
     * @throws DukeException If data could not be read.
     */
    public TaskList load() throws DukeException {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList list = (TaskList) ois.readObject();
            fis.close();
            ois.close();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("No task list found on hard disk. Create a new one...");
        }
    }
}
