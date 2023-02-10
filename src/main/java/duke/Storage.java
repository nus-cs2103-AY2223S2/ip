package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Storage class that handles saving and loading of task data
 */
public class Storage {
    private static final String SAVED_FILE_PATH = "./savedTasks.txt";
    private static final String IO_EXCEPTION_MESSAGE = "I/O error occurred while retrieving list from save.";
    private static final String FILE_HANDLING_EXCEPTION_MESSAGE =
            "File error occurred while retrieving list from save.";

    private String saveFilePath;

    /**
     * Constructor for Storage to store file path of saved file
     */
    Storage() {
        saveFilePath = SAVED_FILE_PATH;
    }

    /**
     * Recovers tasks from save file
     * @return List of tasks
     * @throws DukeException If save file path or save file itself is invalid
     */
    public TaskList recoverList() throws DukeException {
        // Check if file does not exist
        File saveFile = new File(saveFilePath);
        if (!saveFile.exists()) {
            return new TaskList();
        }

        // Serialize task list from existing file
        try (FileInputStream fileInputStream = new FileInputStream(saveFilePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (TaskList) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new DukeException(FILE_HANDLING_EXCEPTION_MESSAGE);
        } catch (IOException e) {
            throw new DukeException(IO_EXCEPTION_MESSAGE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves current list of tasks to file
     * @param tasks List of tasks
     * @throws DukeException If save file path or save file itself is invalid
     */
    public void saveTaskChangesToFile(TaskList tasks) throws DukeException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(SAVED_FILE_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(tasks);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            throw new DukeException(FILE_HANDLING_EXCEPTION_MESSAGE);
        } catch (IOException e) {
            throw new DukeException(IO_EXCEPTION_MESSAGE);
        }

    }
    // TODO: Wrapper for try-with involving read write to file (Fn)
}
