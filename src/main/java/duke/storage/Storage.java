package duke.storage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import duke.action.Task;
import duke.data.TypeOfTask;
import duke.exception.DukeException;


/**
 * Represents storage class that manages the loading and saving of the data before and after the program's execution.
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Storage {
    private String filePath;

    /**
     * Creates default constructor. Constructor specifies hard codded file using relative path.
     */
    public Storage() {
        this.filePath = "src" + File.separator + "main" + File.separator
                + "java" + File.separator + "duke" + File.separator
                + "data" + File.separator + "duke.txt";
    }

    /**
     * Creates another constructor that accepts a custom and different filepath
     *
     * @param filePath File path of the data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns deserialized list of tasks after loading stored data from the disk.
     *
     * @return List of tasks
     * @throws DukeException when file is not found or data is corrupted
     */
    public List<Task> loadTasks() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try (ObjectInputStream load = new ObjectInputStream(new FileInputStream(this.filePath))) {
            taskList = (List<Task>) load.readObject();
            //return taskList;
        } catch (Exception e) {
            throw new DukeException(TypeOfTask.storage, 0);
        } finally {
            return taskList;
        }
    }

    /**
     * saves data to disk from program as serialized objects
     *
     * @param taskList List of tasks
     * @throws DukeException when error occurs during saving
     */
    public void saveTasks(List<Task> taskList) throws DukeException {
        try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(this.filePath))) {
            save.writeObject(taskList);
        } catch (Exception e) {
            System.out.println(e);
            //e.printStackTrace();
            throw new DukeException(TypeOfTask.storage, 1);
        }
    }

}
