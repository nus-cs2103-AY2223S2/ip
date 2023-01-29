package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import duke.task.TaskList;

/**
 * Storage handles the loading and saving of
 * the task list.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class Storage {
    private static final String FOLDER = "./";
    private File taskStorage;

    /**
     * Constructor of Storage.
     *
     * @param fileName Filename of saved task list.
     */
    public Storage(String fileName) {
        this.taskStorage = new File(FOLDER, fileName + ".txt");
    }

    /**
     * Saves the task list onto the hard drive.
     * @param tasks The list of tasks.
     */
    public void saveToDisk(TaskList tasks) {
        if (this.taskStorage.exists()) {
            this.taskStorage.delete();
        }
        try {
            this.taskStorage.createNewFile();
            FileWriter fw = new FileWriter(this.taskStorage);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
