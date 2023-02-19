package duke.tool;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected File dir;
    protected File file;

    public Storage(String dir_name, String file_name) {
        if (dir_name.isBlank()) {
            dir_name = "data";
        }
        if (file_name.isBlank()) {
            file_name = "tolist.txt";
        }
        this.dir = new File(dir_name);
        this.file = new File(file_name);
    }

    public static void print(String str) {
        System.out.println(str);
    }

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
