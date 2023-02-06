package duke;

import duke.Tasks.Task;
import duke.Tasks.TaskList;

import java.io.*;

/**
 * Represents an abstraction that helps in dealing with files
 */
public class Storage {

    BufferedReader content;
    FileReader fileReader;
    File file;
    String filepath;

    /**
     * A constructor to specify the path
     * @param filepath the path to save the file and load the file from
     * @throws IOException If the path input is invalid
     */
    public Storage(String filepath) throws IOException{
        this.filepath = filepath;
        this.file = new File(filepath);
        if (this.file.createNewFile()) {
            System.out.println("Creating File...");
        }
        this.fileReader = new FileReader(this.file);
    }

    /**
     * Loads tasks from the file.
     *
     * @return reader object for the file
     */
    public BufferedReader load() {
        if (this.content != null) {
            return content;
        }
        this.content = new BufferedReader(this.fileReader);
        return this.content;
    }

    /**
     * Updates the file and stores the tasklist into the file
     * @param tasks The tasks to be written in the file
     * @throws IOException If the file cannot be created or written in
     */
    public void store(TaskList tasks) throws IOException {
        //noinspection ResultOfMethodCallIgnored
        this.file.createNewFile();
        FileWriter writer = new FileWriter(this.filepath);
        for (Task task: tasks) {
            writer.write(task.toString() + "\n");
        }
        writer.close();
    }
}
