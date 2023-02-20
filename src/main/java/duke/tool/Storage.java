package duke.tool;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a storage class that handles saving and loading to disk jobs.
 */
public class Storage {
    protected File dir;
    protected File file;

    /**
     * Constructs a storage object.
     * @param dir_name The name of a local directory to write. Defaults to "data".
     * @param file_name The name of a file within the directory to write into. Defaults to "tolist.txt"
     */
    public Storage(String dir_name, String file_name) {
        assert ((dir_name != null) && (file_name != null)) : "please provide a valid path to save task list";
        if (dir_name.isBlank()) {
            dir_name = "data";
        }
        if (file_name.isBlank()) {
            file_name = "todo_list.txt";
        }
        this.dir = new File(dir_name);
        this.file = new File(file_name);
    }

    public static void print(String str) {
        System.out.println(str);
    }

    /**
     * Saves list of tasks to disk.
     * @param tasks The list of tasks to write.
     */
    public void save_to_file(ArrayList<Task> tasks) {
        try {
            if (!this.dir.exists()){
                while (!this.dir.mkdirs()) {
                    print(this.dir.getName() + " created\n");
                }
            }

            if (this.file.createNewFile()) {
                print(this.file.getName() + " created\n");
            }

            FileWriter fw = new FileWriter(this.file, false);
            if (tasks.isEmpty()) {
                return;
            } else {
                for (Task t : tasks) {
                    String desc = t.toString() + "\n";
                    fw.write(desc);
                }
            }
            fw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
