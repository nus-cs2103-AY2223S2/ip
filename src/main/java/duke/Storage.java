package duke;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The type Storage.
 */
public class Storage {

    private File taskList;

    private String filepath;

    /**
     * Instantiates a new Storage.
     *
     * @param filepath the filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads saved tasks from hard-disk.
     *
     * @return the list
     * @throws DukeException the duke exception
     */
    public List<String> load() throws DukeException {
        List<String> loadedFile;
        try {
            File existingList = new File(filepath);
            if (!existingList.exists()) {
                existingList.getParentFile().mkdirs();
                existingList.createNewFile();
            }

            assert existingList.exists();

            taskList = existingList;
            loadedFile = Files.readAllLines(Paths.get(taskList.getAbsolutePath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return loadedFile;
    }

    /**
     * Saves tasks to hard-disk.
     *
     * @param tasksToSave the tasks to save
     * @throws IOException the io exception
     */
    public void save(List<String> tasksToSave) throws IOException {
        Files.write(Paths.get(taskList.getAbsolutePath()), tasksToSave);
    }

}
