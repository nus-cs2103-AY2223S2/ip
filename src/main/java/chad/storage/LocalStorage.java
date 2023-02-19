package chad.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;

import chad.exception.InvalidArgumentException;
import chad.parser.InputValidator;
import chad.task.Task;

/**
 * Local storage to store the data from local file.
 */
public class LocalStorage {
    private File file;

    /**
     * Constructor to create a local storage using the data from local file.
     * @param path
     */
    public LocalStorage(String path) {
        File localFile = readFile(path);
        this.file = localFile;
    }

    /**
     * Read from file in local duke.storage.
     * @param path Path to the file in the local duke.storage.
     * @return File obtained from local storage
     */
    public static File readFile(String path) {
        File file = new File(path);
        file.getParentFile().mkdirs();

        if (file.exists()) {
            return file;
        }

        try {
            file.createNewFile();
        } catch (IOException io_error) {
            io_error.printStackTrace();
        }

        return file;
    }

    /**
     * Load tasks from file.
     * @param tasks List to add the tasks read from file.
     */
    public void loadTasks(TaskList tasks) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line = reader.readLine();

            while (line != null) {
                InputValidator.decodeSavedData(line, tasks);
                line = reader.readLine();
            }
            reader.close();
        } catch (DateTimeException d_err) {
            throw new InvalidArgumentException("Wrong date format! Please follow the format "
                    + "YYYY-MM-DD HHmm (e.g. 2000-01-01 2311)");
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Save tasks in the list into local storage before leaving the program.
     * @param tasks list to be saved into the file in local duke.storage.
     */
    public void saveFile(TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            for (Task task: tasks.getTasks()) {
                writer.write(task.toData());
                writer.newLine();
            }
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
