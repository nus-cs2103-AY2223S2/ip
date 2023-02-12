package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.task.Task;

/**
 * A class to handle storing and loading of task list data.
 */
public class Storage {
    // Path of file to store list data.
    private String filePath;

    /**
     * Constructor for Storage class.
     * @param filePath Path to file to store list data (no extension).
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current taskList on file.
     * @throws DukeException If data could not be saved.
     */
    public void save(ArrayList<Task> list) throws DukeException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(list);

            oos.close();
            fos.close();
        } catch (IOException fe) {
            throw new DukeException("Could not save file: " + fe.getMessage());
        }
    }

    /**
     * Loads data from the file path (provided in constructor).
     * @return The TaskList, if found.
     * @throws DukeException If data could not be read.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Task> list = (ArrayList<Task>) ois.readObject();

            fis.close();
            ois.close();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("No task list found on hard disk.");
        }
    }
}
