package duke.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import duke.model.task.TaskList;

/**
 * Reads and writes {@code TaskList} into log a file using static methods.
 */
public class Storage {

    private static final Path PATH_TO_FILE = Path.of(".duke", "tasklist.ser");

    /**
     * Checks whether the log file exists or not. If the log file does not exists, a new log file
     * will be created.
     *
     * @return {@code true} if the log file exists, otherwise {@code false}
     */
    private static boolean createFileIfNotExists() {
        File file = PATH_TO_FILE.toFile();
        if (file.exists()) {
            return false;
        }
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ex) {
            System.err.println("Cannot create new file...");
        }
        return true;
    }

    /**
     * Constructs a {@code TaskList} from the log file. If the log file does not exist, or an
     * exception is caught, an empty {@code TaskList} will be returned.
     *
     * @return a {@code TaskList} instance
     */
    public static TaskList readTaskList() {
        if (createFileIfNotExists()) {
            return new TaskList();
        }
        try (InputStream in = Files.newInputStream(PATH_TO_FILE);
                ObjectInputStream objIn = new ObjectInputStream(in)) {
            return (TaskList) objIn.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Something goes wrong. A new log will be created...");
            return new TaskList();
        }
    }

    /**
     * Writes a {@code TaskList} into the log file. The log file will be created if it does not
     * exist.
     *
     * @param list the {@code TaskList} to be written into the log file
     */
    public static void writeTaskList(TaskList list) {
        createFileIfNotExists();
        try (OutputStream out = Files.newOutputStream(PATH_TO_FILE);
                ObjectOutputStream objOut = new ObjectOutputStream(out)) {
            objOut.writeObject(list);
        } catch (IOException ex) {
            System.err.println("Something goes wrong, cannot write into the log file...");
        }
    }
}
