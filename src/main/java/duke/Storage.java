package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import duke.exceptions.DukeSaveLoadException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The class that handling the saving/loading of persistent data for Duke.
 */
public class Storage {
    /** The data's save file's path. */
    private String filePath;

    /**
     * Initialise a 'Storage' instance.
     *
     * @param filePath The data's save file's path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the contents of a file, and return it.
     *
     * @param filePath The path of the file to be read.
     * @return Contents of the file.
     */
    private static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Writes a string into the file (overwriting the old contents).
     *
     * @param filePath The path of the file to be written to.
     * @param content The contents to write into the file.
     * @throws DukeSaveLoadException If there's an error accessing/writing to the file.
     */
    private static void writeStringToFile(String filePath, String content) throws DukeSaveLoadException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            throw new DukeSaveLoadException("There's an error writing to save file.");
        }
    }

    /**
     * Loads the locally-saved data, and parse it into a 'TaskList' instance.
     *
     * @return The loaded task list from the save file.
     * @throws DukeSaveLoadException If there's an error parsing the save file.
     */
    public TaskList load() throws DukeSaveLoadException {
        String saveFileContents = Storage.readFile(this.filePath);
        TaskList output = new TaskList();
        if (saveFileContents == null || saveFileContents.isBlank()) {
            return output;
        }

        for (String encodedTask : saveFileContents.split("\n")) {
            Task task = Task.loadFromString(encodedTask);
            output.add(task);
        }

        return output;
    }

    /**
     * Saves a task list into a local file, to be loaded later.
     *
     * @param tasks The task list to be saved.
     * @throws DukeSaveLoadException If there's an error accessing/writing to the file.
     */
    public void save(TaskList tasks) throws DukeSaveLoadException {
        Storage.writeStringToFile(this.filePath, tasks.encodeAsString());
    }
}
