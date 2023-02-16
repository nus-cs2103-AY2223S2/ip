package duke.storage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * This storage class represents Duke's storage.
 */
public class Storage {
    private File store;

    /**
     * Constructs the Storage using the file at path s.
     *
     * @param s The path String to the file that will be used as the storage.
     * @throws DukeException If the file has issues.
     */
    public Storage(String s) throws DukeException {
        File f = new File(s);
        store = f;
        try {
            if (!f.exists()) {
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                } else {
                    f.createNewFile();
                }
            } else if (!(f.canRead() && f.canWrite())) {
                throw new DukeException("File at src/main/resources/duke.txt cannot be read/written!");
            }
        } catch (IOException e) {
            throw new DukeException("Failed to create saving directory at" + "src/main/resources/duke.txt");

        } catch (SecurityException e) {
            throw new DukeException("File at src/main/resources/duke.txt cannot be read/written!");
        } finally {
            assert store != null : "store should never be null";
        }

    }

    /**
     * Saves the tasks to the storage file.
     *
     * @param tasks The tasks to save.
     */
    public void saveToFile(TaskList tasks) {
        try {
            //Solution below adapted from https://www.geeksforgeeks.org/serialization-in-java/
            FileOutputStream temp = new FileOutputStream(store);
            ObjectOutputStream out = new ObjectOutputStream(temp);

            out.writeObject(tasks);

            out.close();
            temp.close();
            History.getInstance().addTaskState(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads from the storage file.
     *
     * @return The arraylist containing the tasks.
     * @throws DukeException If the file cannot be read.
     */
    public TaskList loadFromFile() throws DukeException {
        try {
            //Solution below adapted from https://www.geeksforgeeks.org/serialization-in-java/
            FileInputStream temp = new FileInputStream(store);
            ObjectInputStream in = new ObjectInputStream(temp);

            // Any corruption will be handled below.
            TaskList output = (TaskList) in.readObject();

            in.close();
            temp.close();
            History.getInstance().addTaskState(output);
            return output;
        } catch (EOFException e) {
            // do nothing, expected from empty save file
            History.getInstance().addTaskState(new TaskList());
        } catch (Exception e) {
            throw new DukeException("Unable to read file: " + e.getMessage());
        }

        return null;
    }
}
