package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskManager;
import task.ToDo;

/**
 * Represents a file management system for the saving and
 * storage of tasks to and from the hard disk.
 * <p>
 * Contains methods for saving data to a file or uploading
 * data to destination.
 */
public class FileManager implements Serializable {
    private final String filePath;
    private final File file;

    /**
     * Initialises the file manager at a given filepath and
     * creates a new file if it doesn't currently exist.
     * @param filePath
     */
    public FileManager(String filePath) {
        //Credit: 2103T forum issue #192 and @jamieeeleow
        assert filePath != null;
        assert !filePath.isEmpty();
        String userDir = Paths.get("").toAbsolutePath().toString();
        this.filePath = Paths.get(userDir, filePath).toString();

        new File(this.filePath).getParentFile().mkdirs();

        if (!new File(this.filePath).exists()) {
            try {
                new File(this.filePath).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = new File(this.filePath);
    }

    //Credit to @Junyi00 for the simple and easy to understand serialisation method for level-7

    /**
     * Extracts tasks from the arraylist and writes
     * to the file at a specified path.
     *
     * @param taskManager
     */
    public void saveTasksToFile(TaskManager taskManager) throws DukeException {
        try {
            if (!file.isFile() && !file.isDirectory()) {
                throw new DukeException("File or folder not found! Please create the file or folder.");
            }
            FileWriter fw = new FileWriter(file);
            ArrayList<Task> taskArr = taskManager.getTaskArr();

            for (int i = 0; i < taskArr.size(); i++) {
                fw.write(taskArr.get(i).serialise());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IO Error has occured in File Manager!");
        }
    }

    /**
     * Upload data stored in specified file path to
     * the arraylist.
     *
     * @param taskManager
     * @return a 0 to indicate successful load
     *      and -1 to indicate unsuccessful load.
     */
    public int loadDataToArrayList(TaskManager taskManager) {
        try {
            if (!file.isFile()) {
                return -1;
            }
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String data = s.nextLine();
                String id = data.split(",")[0];
                Task task;

                switch (id) {
                case "Todo":
                    task = ToDo.deserialise(data);
                    break;
                case "Event":
                    task = Event.deserialise(data);
                    break;
                case "Deadline":
                    task = Deadline.deserialise(data);
                    break;
                default:
                    task = Task.deserialise(data);
                    break;
                }
                assert taskManager != null;
                taskManager.addTaskToList(task);
            }
        } catch (FileNotFoundException e) {
            return -1;
        } catch (DukeException e) {
            return -1;
        }
        return 0;
    }
}
