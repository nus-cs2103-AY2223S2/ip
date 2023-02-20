package duke.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a storage class that handles saving and loading to disk jobs.
 */
public class Storage {
    protected File dir;
    protected File file;

    /**
     * Constructs a storage object.
     * @param dirName The name of a local directory to write. Defaults to "data".
     * @param fileName The name of a file within the directory to write into. Defaults to "tolist.txt"
     */
    public Storage(String dirName, String fileName) {
        assert ((dirName != null) && (fileName != null)) : "please provide a valid path to save task list";
        if (dirName.isBlank()) {
            dirName = "data";
        }
        if (fileName.isBlank()) {
            fileName = "todo_list.txt";
        }
        this.dir = new File(dirName);
        this.file = new File(fileName);
    }

    public static void print(String str) {
        System.out.println(str);
    }

    /**
     * Saves list of tasks to disk.
     * @param tasks The list of tasks to write.
     */
    public void save_to_file(ArrayList<Task> tasks) {
        if (!this.dir.exists()) {
            while (!this.dir.mkdirs()) {
                print(this.dir.getName() + " created\n");
            }
        }
        try {
            if (this.file.createNewFile()) {
                print(this.file.getName() + " created\n");
            }
            if (tasks.isEmpty()) {
                return;
            }
            FileWriter fw = new FileWriter(this.file, false);
            for (Task t : tasks) {
                String desc = t + "\n";
                fw.write(desc);
            }
            fw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
