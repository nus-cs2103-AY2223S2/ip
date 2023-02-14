package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.task.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    protected Path directory;
    protected Path filePath;
    protected boolean isLoadError = false;

    /**
     * Initialises the location where save data will be stored.
     *
     * @param filePath Filepath where the save data is stored.
     */
    public Storage(String filePath) {
        String root = System.getProperty("user.dir");
        this.directory = Paths.get(root);
        this.filePath = Paths.get(root, filePath);

        int index = filePath.lastIndexOf("/") + 1;
        if (index > 0) {
            this.directory = Paths.get(root,
                    filePath.substring(0, index));
        }
    }

    public boolean isLoadError() {
        return isLoadError;
    }

    public void setLoadError(boolean isLoadError) {
        this.isLoadError = isLoadError;
    }

    /**
     * Checks the directories and filepath of the storage location.
     * If directory does not exist, create the necessary directories.
     * If file does not exist, create the file.
     *
     * @throws DukeException If encountering an I/O interrupt while checking the location of saved data
     */
    private void checkStorage() throws DukeException {
        try {
            // Checks if directory exist, if not create directory
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Checks if filepath exist, if not create directory
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new DukeException("Failed to check storage due to I/O interrupt");
        }
    }

    /**
     * Saves the changes made to the list of task in the hard disk
     *
     * @param tasks List of tasks
     * @throws DukeException If encountering an I/O interrupt while saving data
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            checkStorage();
            String[] saveData = tasks.toSaveData();
            String output = String.join("\n", saveData);

            Files.write(filePath,
                    output.getBytes());
        } catch (IOException e) {
            throw new DukeException("Failed to save data due to I/O interrupt");
        }
    }

    /**
     * Returns the data stored in the hard disk.
     *
     * @return Array tasks' save data.
     * @throws DukeException If encountering an I/O interrupt while loading data.
     */
    public String[] load() throws DukeException {
        try {
            checkStorage();
            return Files.readAllLines(filePath)
                    .toArray(new String[0]);
        } catch (IOException e) {
            throw new DukeException("Loading failed due to I/O interrupt");
        }
    }
}
