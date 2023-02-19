package berry.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import berry.exception.IllegalValueException;
import berry.exception.IncorrectDateException;
import berry.task.Task;
import berry.task.TaskList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    // Default file path used if the user doesn't provide the file name.
    public static final String DEFAULT_STORAGE_FILEPATH = "berry.txt";

    // The filePath given by Berry
    private String filePath;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        this.filePath = filePath;
        if (!isValidPath(filePath)) {
            throw new InvalidStorageFilePathException("file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is valid as a storage file.
     * The file path is considered valid if it ends with '.txt'
     */
    private static boolean isValidPath(String filePath) {
        return filePath.endsWith(".txt");
    }

    /**
     * Saves the data in the task list into the storage file.
     *
     * @param tasks are the tasks to be added into the storage file.
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(createFile());
            for (Task taskToAdd : tasks.getList()) {
                writer.write(taskToAdd.interpretTaskToText() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads the data from this storage file to store into the task list.
     *
     * @return an empty task list if the file does not exist, or is not a regular file.
     *      else returns a task list filled with tasks from the storage file.
     * @throws FileNotFoundException if the file could not be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException, IncorrectDateException {
        ArrayList<Task> listOfTasks = new ArrayList<Task>();

        Scanner sc = new Scanner(createFile());
        while (sc.hasNext()) {
            Task taskToLoad = Task.interpretTextToTask(sc.nextLine());
            listOfTasks.add(taskToLoad);
        }
        return listOfTasks;
    }

    /**
     * Creates a new file.
     *
     * @return the new file created if it does not exist, else returns the existing file.
     */
    private File createFile() {
        File dataFile = new File(filePath);
        File folder = new File(filePath.split("/")[0]);
        try {
            folder.mkdir();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.err.println("Berry cannot open your file :<");
        }
        return dataFile;
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }
}
