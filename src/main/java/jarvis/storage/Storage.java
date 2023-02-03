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
    public static final String DATA_PATH = "data";
    public static final String TASKS_PATH = DATA_PATH + "/tasks.txt";

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
        File folder = new File(DATA_PATH);
        File file = new File(TASKS_PATH);
        try {
            folder.mkdir();
            file.createNewFile();
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
            throw new TaskIoException(
                    "Unable to create tasks file",
                    "There's something wrong in my head."
            );
        }

        return file;
    }
}
