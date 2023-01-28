package berry.storage;

import berry.exception.IllegalValueException;
import berry.task.Task;
import berry.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;
    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "berry.txt";

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        this.filePath = filePath;
        if (!isValidPath(filePath)) {
            throw new InvalidStorageFilePathException("file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(String filePath) {
        return filePath.endsWith(".txt");
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(createFile());
            for (Task task : tasks.getList()) {
                writer.write(task.interpretTaskToString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> listOfTasks = new ArrayList<Task>();

        Scanner sc = new Scanner(createFile());
        while (sc.hasNext()) {
            Task task = Task.interpretStringToTask(sc.nextLine());
            listOfTasks.add(task);
        }
        return listOfTasks;
    }

    private File createFile() {
        File dataFile = new File(filePath);
        File folder =  new File(filePath.split("/")[0]);
        try {
            folder.mkdir();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Please re-name your file.");
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
