package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class to help with loading and storing of Tasks in duke.txt.
 * @author Merrick
 */
public class Storage {
    private final String filePath;
    private final String fileDir;

    /**
     * Constructor of Storage class
     */
    public Storage(String fileDir, String filePath) {
        assert !fileDir.isEmpty() : "File Directory should not be empty!";
        assert !filePath.isEmpty() : "File Path should not be empty!";
        this.fileDir = fileDir;
        this.filePath = filePath;
    }

    /**
     * Loads the tasks stored in the duke.txt file.
     * @return BufferedReader object that contains the list of tasks.
     * @throws DukeException If IOException from reading duke.txt is encountered.
     */
    public BufferedReader loadData() throws DukeException {
        assert !fileDir.isEmpty() : "File Directory cannot be empty!";
        assert !filePath.isEmpty() : "File Path cannot be empty";
        BufferedReader br;
        try {
            File directory = new File(this.fileDir);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            throw new DukeException("Unable to read from file, creating a new file");
        }
        return br;
    }

    /**
     * Saves the tasks stored TaskList into the duke.txt file.
     * @throws IOException If IOException from writing to duke.txt is encountered.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            String tl = tasks.saveTaskList();
            fw.write(tl);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save task data");
        }
    }
}
