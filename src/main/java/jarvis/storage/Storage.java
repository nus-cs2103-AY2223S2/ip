package jarvis.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import jarvis.exception.TaskIoException;
import jarvis.task.Task;

/**
 * Storage class to handle local storage of tasks.
 */
public class Storage {
    public static final String FRIENDLY_ERROR_MESSAGE = "There's something wrong with my head.";

    private final String fileName;
    private final String[] folderNames;

    /**
     * Constructor for Storage.
     *
     * @param fileName Name of the file (e.g. "tasks.txt").
     * @param folderNames Names of the individual folders in the path
     *                    that leads up to fileName, without leading slashes
     *                    (e.g. "data", "tasks").
     */
    public Storage(String fileName, String ...folderNames) {
        this.fileName = fileName;
        this.folderNames = folderNames;
    }

    /**
     * Reads tasks from local storage.
     *
     * @return List of tasks (can be empty).
     */
    public List<Task> readTasks() {
        List<Task> tasks = new LinkedList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(this.getFile());
        } catch (FileNotFoundException | TaskIoException e) {
            return tasks;
        }

        while (scanner.hasNextLine()) {
            Task task = Task.deserialize(scanner.nextLine());
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to local storage.
     *
     * @param tasks List of tasks to save.
     * @throws TaskIoException If the tasks cannot be saved.
     */
    public void saveTasks(List<Task> tasks) throws TaskIoException {
        assert tasks != null;
        try {
            FileWriter writer = new FileWriter(this.getFile());
            for (Task task : tasks) {
                writer.write(task.serialize());
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            throw new TaskIoException("Unable to save tasks");
        }
    }

    /**
     * Creates the folder and file to save tasks, if necessary.
     *
     * @return The created file.
     * @throws TaskIoException If the folder or file cannot be created or accessed.
     */
    private File getFile() throws TaskIoException {
        String path = "";
        for (String folderName : folderNames) {
            if (path.length() > 0) {
                path += "/";
            }
            path += folderName;
            try {
                new File(path).mkdir();
            } catch (SecurityException e) {
                throw new TaskIoException(
                        String.format("Unable to create folder '%s'", path),
                        FRIENDLY_ERROR_MESSAGE
                );
            }
        }

        File file = new File(String.join("/", path, fileName));
        try {
            file.createNewFile();
        } catch (IOException | SecurityException e) {
            throw new TaskIoException(
                    String.format("Unable to create file '%s'", path),
                    FRIENDLY_ERROR_MESSAGE
            );
        }
        return file;
    }
}
