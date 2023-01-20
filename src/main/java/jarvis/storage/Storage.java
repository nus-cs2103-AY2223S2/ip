package jarvis.storage;

import jarvis.exception.CommandParseException;
import jarvis.exception.TaskIOException;
import jarvis.task.DeadlineTask;
import jarvis.task.EventTask;
import jarvis.task.Task;
import jarvis.task.ToDoTask;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
        } catch (FileNotFoundException | TaskIOException e) {
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
     * @throws TaskIOException If the tasks cannot be saved.
     */
    public void saveTasks(List<Task> tasks) throws TaskIOException {
        try {
            FileWriter writer = new FileWriter(this.getFile());
            for (Task task : tasks) {
                writer.write(task.serialize());
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            throw new TaskIOException(
                    "Unable to save tasks",
                    "I couldn't save your tasks."
            );
        }
    }

    /**
     * Creates the folder and file to save tasks, if necessary.
     *
     * @return The created file.
     * @throws TaskIOException If the folder or file cannot be created or accessed.
     */
    private File getFile() throws TaskIOException {
        File folder = new File(DATA_PATH);
        File file = new File(TASKS_PATH);
        try {
            folder.mkdir();
            file.createNewFile();
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
            throw new TaskIOException(
                    "Unable to create tasks file",
                    "There's something wrong in my head."
            );
        }

        return file;
    }
}
