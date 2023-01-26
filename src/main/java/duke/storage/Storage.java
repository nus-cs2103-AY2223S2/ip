package duke.storage;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import duke.task.TaskList;

public class Storage {
    File taskStorage;
    String FOLDER = "./";

    public Storage(String fileName) {
        this.taskStorage = new File(FOLDER, fileName + ".txt");
    }

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
