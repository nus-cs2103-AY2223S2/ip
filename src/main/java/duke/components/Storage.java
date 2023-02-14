package duke.components;

import duke.exceptions.DukeException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * This is the Storage class for Duke, the CLI task manager.
 * This class handles all loading and storing of the TaskList
 * object that acts as Duke's memory.
 */

public class Storage {
    private String filePath;

    /**
     * Creates a Storage object.
     * The filePath must lead to where DukeMem should be stored.
     *
     * @param filePath the filePath to where DukeMem should be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads DukeMem from storage.
     *
     * @return TaskList object previously saved by Duke.
     * @throws DukeException if error occurred in loading from storage.
     */

    public TaskList load() throws DukeException {
        try {
            FileInputStream readData = new FileInputStream(filePath);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            return (TaskList) readStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves current TaskList object to storage to be retrieved
     * in the next user session.
     *
     * @param tasks TaskList object to be saved.
     * @throws DukeException if error occurred in writing to storage.
     */
    public void updateStorage(TaskList tasks) throws DukeException {
        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(tasks);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
