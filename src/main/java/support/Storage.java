package support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import task.TaskList;

/**
 * Represents a database.
 */
public class Storage {
    private File file;

    /**
     * Creates a directory data, and file duke.txt if it does not exist.
     */
    public Storage() {
        String cwd = System.getProperty("user.dir");
        Path dir = Paths.get(cwd, "data");
        Path data = Paths.get(cwd, "data", "duke.txt");

        try {
            if (Files.exists(data)) {
                this.file = new File(String.valueOf(data));
            } else if (Files.exists(dir)) {
                this.file = new File("data/duke.txt");
            } else {
                Files.createDirectories(dir);
                this.file = new File("data/duke.txt");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Writes data to file after program finishes execution.
     * @param s data to be written
     */
    public void update(String s) {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Reads from data stored for current process.
     * @param l Current taskList to add to
     */
    public void updateTasks(TaskList l) {
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                readTask(l, reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something horribly wrong has occurred.");
        }
    }

    private void readTask(TaskList t, String task) {
        t.addFromFile(task);
    }
}
