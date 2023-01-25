import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import java.util.List;

public class Storage {
    File taskStorage;
    String FOLDER = "./";

    public Storage(String filePath) {
        this.taskStorage = new File(FOLDER, filePath);
    }

    public void write(List<Task> tasks) {
        if (this.taskStorage.exists()) {
            this.taskStorage.delete();
        }
        try {
            this.taskStorage.createNewFile();
            FileWriter fw = new FileWriter(this.taskStorage);
            for (Task t : tasks) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
