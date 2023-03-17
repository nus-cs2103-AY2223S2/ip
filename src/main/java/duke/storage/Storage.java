package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.entities.SerializableTask;
import duke.entities.Task;
import duke.entities.managers.CacheManager;
import duke.enums.TaskType;
import duke.exceptions.DukeException;
import duke.utils.Loader;

/**
 * Represents a Storage class which save the tasks in the hard disk automatically whenever the task list changes.
 * Load the data from the hard disk when duke.Duke starts up.
 */
public class Storage implements Loader<CacheManager> {
    private static final String GENERIC_ERROR = "An error occurred when creating the database: ";
    private final File file;

    /**
     * Initializes a Storage object with the specified filename.
     *
     * @param filename Filename to store the data.
     */
    public Storage(String filename) {
        String fileDirectory = "data";
        this.file = new File(String.format("%s/%s", fileDirectory, filename));
    }

    /**
     * Loads the data stored in the hard drive.
     *
     * @throws DukeException A generic application error that specifies the type of error thrown.
     */
    public void connect() throws DukeException {
        File folder = file.getParentFile();
        try {
            if (!folder.exists() && !folder.mkdirs()) {
                throw new DukeException(GENERIC_ERROR + folder.getName());
            }
            if (!file.exists() && !file.createNewFile()) {
                throw new DukeException(GENERIC_ERROR + file.getName());
            }
            System.out.println("Successfully connected to duke.storage.");
        } catch (IOException | SecurityException err) {
            throw new DukeException(GENERIC_ERROR + err.getMessage());
        }
    }

    /**
     * Loads the data from the specified filename.
     *
     * @param cacheManager TaskList to add the loaded data to.
     * @return A boolean value indicating the success of the operation.
     * @throws DukeException An exception to be thrown if there are any errors that occur.
     */
    public Boolean load(CacheManager cacheManager) throws DukeException {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                List<String> data = Arrays.asList(reader.nextLine().split(" \\| "));

                // Parse task attributes from data
                // Expect only valid data to be saved to hard drive
                TaskType taskType = getTaskType(data);
                boolean isDone = isDone(data);
                String description = data.get(2);

                SerializableTask task;
                switch(taskType) {
                case TODO:
                case DEADLINE:
                case EVENT:
                    String aFlags = data.subList(3, data.size()).toString();
                    String flags = aFlags.substring(1, aFlags.length() - 1);
                    task = new SerializableTask(taskType, isDone, description, flags);
                    break;
                default: task = null;
                }
                if (task == null) {
                    return false;
                }
                String msg = cacheManager.addTask(task.unmarshal(), false);
                if (msg != null) {
                    System.out.println(msg);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            throw new DukeException(GENERIC_ERROR + e.getMessage());
        }
    }

    private static boolean isDone(List<String> data) {
        assert data.get(1).equals("1") || data.get(1).equals("0") : "status can only be yes or no";
        return data.get(1).equals("1");
    }

    private static TaskType getTaskType(List<String> data) {
        assert !data.isEmpty() : "data cannot be empty!";
        assert Arrays.stream(TaskType.values()).anyMatch(i -> Objects.equals(i.getType(), data.get(0)));

        // Expect only valid data to be saved to hard drive
        return TaskType.valueOf(data.get(0).toUpperCase());
    }

    /**
     * Writes all task currently in memory to the hard disk.
     *
     * @param cacheManager The task-list in memory.
     * @throws DukeException An exception to be thrown if there are any errors that occur.
     */
    public void writeAll(CacheManager cacheManager) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            cacheManager.getTaskList().parallelStream().forEach(task -> {
                try {
                    write(fileWriter, task);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            fileWriter.close();
        } catch (IOException | RuntimeException e) {
            throw new DukeException(GENERIC_ERROR + e.getMessage());
        }
    }

    private static void write(FileWriter fileWriter, Task task) throws IOException {
        assert task != null : "cannot write empty task to hard disk";

        SerializableTask tsk = task.serialize();
        fileWriter.write(tsk.marshal() + "\n");
    }

    /**
     * Append current task in memory to the hard disk.
     *
     * @param task The task in memory.
     * @throws DukeException An exception to be thrown if there are any errors that occur.
     */
    public void writeOne(Task task) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            write(fileWriter, task);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(GENERIC_ERROR + e.getMessage());
        }
    }
}
