package util;

import java.io.*;

/**
 * Storage class to help with loading and storing of Tasks in duke.txt.
 *
 * @author Merrick
 */
public class Storage {
    private String filePath;
    private String fileDir;

    /**
     * Constructor of Storage class
     * @param fileDir Directory of duke.txt file.
     * @param filePath Filepath of duke.txt file.
     */
    public Storage(String fileDir, String filePath) {
        // check if fileDir and filePath are not empty
        this.fileDir = fileDir;
        this.filePath = filePath;
    }

    /**
     * Loads the tasks stored in the duke.txt file.
     * @return BufferedReader object that contains the list of tasks.
     * @throws DukeException If IOException from reading duke.txt is encountered.
     */
    public BufferedReader load() throws DukeException {
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
     * @throws DukeException If IOException from writing to duke.txt is encountered.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            String tl = tasks.saveTaskList();
            fw.write(tl);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
