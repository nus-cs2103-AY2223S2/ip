package duke.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Creates the Storage class.
 */
public class Storage {
    /** Filepath of TaskList */
    private final Path filename;

    /**
     * Constructs a Storage.
     *
     * @param filename Filename of TaskList.
     */
    public Storage(String filename) {
        String[] parts = filename.split("/");
        this.filename = Paths.get(parts[0], parts[1], parts[2]);
    }

    /**
     * Saves TaskList.
     *
     * @param tasks TaskList to be saved.
     * @throws DukeException If file cannot be found.
     */
    public void save(TaskList tasks) throws DukeException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename.toString()))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads TaskList.
     *
     * @return Loaded TaskList.
     * @throws DukeException If file cannot be found or if classes from the file cannot be found.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        File file = filename.toFile();
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        if (file.length() != 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename.toString()))) {
                tasks = (TaskList) ois.readObject();
            } catch (ClassNotFoundException | IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return tasks;
    }
}
