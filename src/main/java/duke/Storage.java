package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.TaskList;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructor for Storage.
     * @param filePath The path of the local file where tasks are stored from the previous session.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves saved tasks from local file.
     * @return The list of tasks stored in the local file.
     * @throws DukeException If the directory that stores the tasks data does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            File tasksData = this.filePath.toFile();
            if (!tasksData.exists()) {
                tasksData.createNewFile();
            }

            Scanner fileReader = new Scanner(tasksData);
            while (fileReader.hasNextLine()) {
                try {
                    String storedTask = fileReader.nextLine();
                    Task task = Parser.parseStoredTask(storedTask);

                    /* Add the task to taskList */
                    tasks.add(task);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
            }
            fileReader.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("The directory that stores the tasks data does not exist...");
        }
    }

    /**
     * Saves the current tasks from the task list into local file.
     * @param taskList The latest updated task list of the current chat session.
     * @throws DukeException If the directory that stores the tasks data does not exist.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            ArrayList<Task> tasks = taskList.list();

            File tasksData = this.filePath.toFile();
            tasksData.createNewFile();

            FileWriter fileWriter = new FileWriter(tasksData);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fileWriter.write(task.getSavedFormat());
                fileWriter.write('\n');
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("The directory that stores the tasks data does not exist...");
        }
    }
}
